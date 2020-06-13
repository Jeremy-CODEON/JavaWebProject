package org.fkit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.entity.ContentType;

import org.fkit.domain.Music;
import org.fkit.domain.Order;
import org.fkit.domain.OrderMusic;
import org.fkit.domain.User;
import org.fkit.service.BehaviorService;
import org.fkit.service.CartService;
import org.fkit.service.CateLogService;
import org.fkit.service.LogService;
import org.fkit.service.MusicService;
import org.fkit.service.OrderMusicService;
import org.fkit.service.OrderService;
import org.fkit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import othersPOJO.MailUtil;

@Controller
public class OrderController {
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	@Autowired
	@Qualifier("musicService")
	private MusicService musicService;
	@Autowired
	@Qualifier("orderService")
	private OrderService orderService;
	@Autowired
	@Qualifier("orderMusicService")
	private OrderMusicService orderMusicService;
	@Autowired
	@Qualifier("cartService")
	private CartService cartService;
	@Autowired
	@Qualifier("logService")
	private LogService logService;
	@Autowired
	@Qualifier("cateLogService")
	private CateLogService cateLogService;
	@Autowired
	@Qualifier("behaviorService")
	private BehaviorService behaviorService;

	// 跳转确认订单页面
	@RequestMapping(value = "/orderConfirmPage")
	public ModelAndView orderConfirmPage(String purchaseList, ModelAndView mv, HttpSession session) {
		System.out.println(purchaseList);

		User user = (User) session.getAttribute("user");

		if (user == null) {
			mv.addObject("message", "用户未登录！");
			mv.setViewName("index");
		} else {
			if (purchaseList == null) {
				mv.addObject("message", "未选择音乐！");
				mv.setViewName("userInfo");
				return mv;
			}

			// 分解checkbox值
			String[] musicIdList = purchaseList.split(",");

			// System.out.println(musicIdList[0]);

			// 用用户的id值创建新订单
			orderService.createOrder(user.getId());
			// 获取刚刚新建的订单
			Order order = orderService.getOrderCreated();

			for (String musicId : musicIdList) {
				// System.out.println(musicId);
				// 将订单号转为int类型
				int musicid = Integer.parseInt(musicId);
				// 获取单曲价格，加到订单总额中
				Music music = musicService.getById(musicid);
				orderService.addMusicCost(music.getCost(), order.getId());
				// 在订单-音乐映射表中加入音乐
				orderMusicService.addMusicIntoOrder(order.getId(), music.getId());
				// 从购物车里面移除音乐
				cartService.removeFromCartByUserIdAndMusicId(user.getId(), music.getId());
			}

			// 获得更新后的订单
			Order forConfirmOrder = orderService.getById(order.getId());
			// 订单和音乐映射
			List<OrderMusic> forConfirmOrderMusicList = orderMusicService.getByOrderId(order.getId());

			// 传数据到确认订单页面
			mv.addObject("forConfirmOrder", forConfirmOrder);
			mv.addObject("forConfirmOrderMusicList", forConfirmOrderMusicList);

			// System.out.println(forConfirmOrderMusicList.toString());

			mv.addObject("message", "订单生成成功！待确认");
			mv.setViewName("confirmOrder");
		}
		return mv;
	}

	// 确认订单
	@RequestMapping(value = "/confirmOrder", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String confirmOrder(int order_id, ModelAndView mv, HttpSession session, HttpServletRequest request) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "{\"message\": \"该用户未登录！\"}";
		} else {
			
			// 更新各个音乐的销售量
			orderService.updateMusicListPurchasesInOrder(order_id);
			
			// 更改订单状态
			orderService.updateState(2, order_id);

			// 插入用户购买音乐的日志记录
			Date date = new Date();
			Calendar cal=Calendar.getInstance();
			List<OrderMusic> orderMusicList = orderService.getOrderMusicByOrderId(order_id);
			for (OrderMusic orderMusic : orderMusicList) {
				//为管理员插入日志
				logService.addLog(user.getId(), "用户(" + user.getLoginname() + ")购买音乐-->音乐id:"
						+ orderMusic.getMusic().getId() + "-" + orderMusic.getMusic().getName(), date.toString());
				//为销售插入日志
				cateLogService.addCateLog(user.getId(), orderMusic.getMusic().getCategory(), "用户(" + user.getLoginname() + ")购买音乐-->音乐id:"
						+ orderMusic.getMusic().getId() + "-" + orderMusic.getMusic().getName(), date.toString());
				//浏览和购买记录
				behaviorService.addBehavior(user.getId(), orderMusic.getMusic().getId(), 2, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DATE));
				//更新用户分类信息
				userService.classifyUser(user.getId(), user.getAge());
			}
			
			
			// 使用依赖注入获取邮件发送类
			ApplicationContext context = new ClassPathXmlApplicationContext("application-mail.xml");
			MailUtil mailUtil = (MailUtil) context.getBean("mailUtil");
			((ConfigurableApplicationContext) context).close();

			// 获取路径
			String path = request.getServletContext().getRealPath("/MUSICS");
			System.out.println(path);
			File musicFile = new File(path + File.separator + "星茶会.mp3");
			FileInputStream fileInputStream;
			try {
				fileInputStream = new FileInputStream(musicFile);
				try {
					// 封装附件
					MultipartFile multipartFile = new MockMultipartFile(musicFile.getName(), musicFile.getName(),
							ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);

					Order order = orderService.getById(order_id);
					// 邮件主题
					String mailTitle = "音乐充电小店：订单确认";
					// 邮件内容
					String mailContent = "来自音乐充电小店的订单确认信息：\n您的订单（编号：" + order.getId() + "，总额：" + order.getCostSum()
							+ ")已经被支付成功！\n订单详情请访问音乐充电小店个人中心查看。\n您的音乐单曲已经通过附件的形式发送给您，请点击邮件的附件自行进行下载。\n对订单有任何疑问请联系@音乐充电小店官方客服。\n感谢您支持我们的产品！";
					
					System.out.println("邮件内容已经生成！");
					
					mailUtil.sendWithFile(user.getMail(), mailTitle, mailContent, multipartFile);

				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
//			// 发送邮件（无附件）
//			Order order = orderService.getById(order_id);
//			// 邮件主题
//			String mailTitle = "音乐充电小店：订单确认";
//			// 邮件内容
//			String mailContent = "来自音乐充电小店的订单确认信息：\n您的订单（编号：" + order.getId() + "，总额：" + order.getCostSum()
//					+ ")已经被支付成功！\n订单详情请访问音乐充电小店个人中心查看。\n对订单有任何疑问请联系@音乐充电小店官方客服。\n感谢您支持我们的产品！";
//			
//			//System.out.println("邮件内容已经生成！");
//			
//			mailUtil.send(user.getMail(), mailTitle, mailContent);

			return "{\"message\": \"订单支付成功！\"}";
		}
	}

	// 跳转到订单详情页
	@RequestMapping(value = "/orderDetail/{id}")
	public ModelAndView orderDetail(@PathVariable("id") int id, ModelAndView mv) {
		// System.out.println("hhh1");
		Order order = orderService.getById(id);
		// System.out.println("hhh2");
		mv.addObject("order", order);
		mv.setViewName("orderDetail");
		return mv;
	}

}
