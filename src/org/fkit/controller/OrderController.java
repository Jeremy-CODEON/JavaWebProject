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

	// ��תȷ�϶���ҳ��
	@RequestMapping(value = "/orderConfirmPage")
	public ModelAndView orderConfirmPage(String purchaseList, ModelAndView mv, HttpSession session) {
		System.out.println(purchaseList);

		User user = (User) session.getAttribute("user");

		if (user == null) {
			mv.addObject("message", "�û�δ��¼��");
			mv.setViewName("index");
		} else {
			if (purchaseList == null) {
				mv.addObject("message", "δѡ�����֣�");
				mv.setViewName("userInfo");
				return mv;
			}

			// �ֽ�checkboxֵ
			String[] musicIdList = purchaseList.split(",");

			// System.out.println(musicIdList[0]);

			// ���û���idֵ�����¶���
			orderService.createOrder(user.getId());
			// ��ȡ�ո��½��Ķ���
			Order order = orderService.getOrderCreated();

			for (String musicId : musicIdList) {
				// System.out.println(musicId);
				// ��������תΪint����
				int musicid = Integer.parseInt(musicId);
				// ��ȡ�����۸񣬼ӵ������ܶ���
				Music music = musicService.getById(musicid);
				orderService.addMusicCost(music.getCost(), order.getId());
				// �ڶ���-����ӳ����м�������
				orderMusicService.addMusicIntoOrder(order.getId(), music.getId());
				// �ӹ��ﳵ�����Ƴ�����
				cartService.removeFromCartByUserIdAndMusicId(user.getId(), music.getId());
			}

			// ��ø��º�Ķ���
			Order forConfirmOrder = orderService.getById(order.getId());
			// ����������ӳ��
			List<OrderMusic> forConfirmOrderMusicList = orderMusicService.getByOrderId(order.getId());

			// �����ݵ�ȷ�϶���ҳ��
			mv.addObject("forConfirmOrder", forConfirmOrder);
			mv.addObject("forConfirmOrderMusicList", forConfirmOrderMusicList);

			// System.out.println(forConfirmOrderMusicList.toString());

			mv.addObject("message", "�������ɳɹ�����ȷ��");
			mv.setViewName("confirmOrder");
		}
		return mv;
	}

	// ȷ�϶���
	@RequestMapping(value = "/confirmOrder", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String confirmOrder(int order_id, ModelAndView mv, HttpSession session, HttpServletRequest request) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "{\"message\": \"���û�δ��¼��\"}";
		} else {
			
			// ���¸������ֵ�������
			orderService.updateMusicListPurchasesInOrder(order_id);
			
			// ���Ķ���״̬
			orderService.updateState(2, order_id);

			// �����û��������ֵ���־��¼
			Date date = new Date();
			Calendar cal=Calendar.getInstance();
			List<OrderMusic> orderMusicList = orderService.getOrderMusicByOrderId(order_id);
			for (OrderMusic orderMusic : orderMusicList) {
				//Ϊ����Ա������־
				logService.addLog(user.getId(), "�û�(" + user.getLoginname() + ")��������-->����id:"
						+ orderMusic.getMusic().getId() + "-" + orderMusic.getMusic().getName(), date.toString());
				//Ϊ���۲�����־
				cateLogService.addCateLog(user.getId(), orderMusic.getMusic().getCategory(), "�û�(" + user.getLoginname() + ")��������-->����id:"
						+ orderMusic.getMusic().getId() + "-" + orderMusic.getMusic().getName(), date.toString());
				//����͹����¼
				behaviorService.addBehavior(user.getId(), orderMusic.getMusic().getId(), 2, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DATE));
				//�����û�������Ϣ
				userService.classifyUser(user.getId(), user.getAge());
			}
			
			
			// ʹ������ע���ȡ�ʼ�������
			ApplicationContext context = new ClassPathXmlApplicationContext("application-mail.xml");
			MailUtil mailUtil = (MailUtil) context.getBean("mailUtil");
			((ConfigurableApplicationContext) context).close();

			// ��ȡ·��
			String path = request.getServletContext().getRealPath("/MUSICS");
			System.out.println(path);
			File musicFile = new File(path + File.separator + "�ǲ��.mp3");
			FileInputStream fileInputStream;
			try {
				fileInputStream = new FileInputStream(musicFile);
				try {
					// ��װ����
					MultipartFile multipartFile = new MockMultipartFile(musicFile.getName(), musicFile.getName(),
							ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);

					Order order = orderService.getById(order_id);
					// �ʼ�����
					String mailTitle = "���ֳ��С�꣺����ȷ��";
					// �ʼ�����
					String mailContent = "�������ֳ��С��Ķ���ȷ����Ϣ��\n���Ķ�������ţ�" + order.getId() + "���ܶ" + order.getCostSum()
							+ ")�Ѿ���֧���ɹ���\n����������������ֳ��С��������Ĳ鿴��\n�������ֵ����Ѿ�ͨ����������ʽ���͸����������ʼ��ĸ������н������ء�\n�Զ������κ���������ϵ@���ֳ��С��ٷ��ͷ���\n��л��֧�����ǵĲ�Ʒ��";
					
					System.out.println("�ʼ������Ѿ����ɣ�");
					
					mailUtil.sendWithFile(user.getMail(), mailTitle, mailContent, multipartFile);

				} catch (IOException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			
//			// �����ʼ����޸�����
//			Order order = orderService.getById(order_id);
//			// �ʼ�����
//			String mailTitle = "���ֳ��С�꣺����ȷ��";
//			// �ʼ�����
//			String mailContent = "�������ֳ��С��Ķ���ȷ����Ϣ��\n���Ķ�������ţ�" + order.getId() + "���ܶ" + order.getCostSum()
//					+ ")�Ѿ���֧���ɹ���\n����������������ֳ��С��������Ĳ鿴��\n�Զ������κ���������ϵ@���ֳ��С��ٷ��ͷ���\n��л��֧�����ǵĲ�Ʒ��";
//			
//			//System.out.println("�ʼ������Ѿ����ɣ�");
//			
//			mailUtil.send(user.getMail(), mailTitle, mailContent);

			return "{\"message\": \"����֧���ɹ���\"}";
		}
	}

	// ��ת����������ҳ
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
