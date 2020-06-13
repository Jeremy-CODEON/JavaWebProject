package org.fkit.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.fkit.domain.Cart;
import org.fkit.domain.CateLog;
import org.fkit.domain.Log;
import org.fkit.domain.Music;
import org.fkit.domain.Order;
import org.fkit.domain.OrderMusic;
import org.fkit.domain.Saler;
import org.fkit.domain.User;
import org.fkit.service.CartService;
import org.fkit.service.CateLogService;
import org.fkit.service.LogService;
import org.fkit.service.MusicService;
import org.fkit.service.OrderService;
import org.fkit.service.SalerService;
import org.fkit.service.UserService;
import org.fkit.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import othersPOJO.IPUtil;
import othersPOJO.StatisCategoryInfo;

@Controller
public class SalerController {

	@Autowired
	@Qualifier("salerService")
	private SalerService salerService;
	@Autowired
	@Qualifier("musicService")
	private MusicService musicService;
	@Autowired
	@Qualifier("cartService")
	private CartService cartService;
	@Autowired
	@Qualifier("orderService")
	private OrderService orderService;
	@Autowired
	@Qualifier("statisticService")
	private StatisticService statisticService;
	@Autowired
	@Qualifier("cateLogService")
	private CateLogService cateLogService;
	@Autowired
	@Qualifier("logService")
	private LogService logService;
	
	@Autowired
    HttpServletRequest req;

	// 销售登录
	@PostMapping(value = "/loginSaler")
	public ModelAndView login(Integer loginid, String password, ModelAndView mv, HttpSession session) {
		Saler saler = salerService.login(loginid, password);
		// System.out.println(loginid);
		// System.out.println(password);
		if (saler != null) 
		{
			// 获取销售客户端ip
			String ip=IPUtil.getIP(req);
			// 获取日期
			Date date = new Date();
			//记录销售日志
			logService.addLog(990000+saler.getId(), "销售(编号：" + saler.getId() + ")登录平台，IP="+ip, date.toString());
			
			session.setAttribute("saler", saler);
			mv.setView(new RedirectView("/SSMdemo/salerEnter"));// 转向Servlet
		} 
		else 
		{
			mv.addObject("message", "账户或者密码错误！");
			mv.setViewName("loginForm");
		}
		return mv;
	}

	// 进入销售管理页面
	@RequestMapping(value = "/salerEnter")
	public ModelAndView manage(ModelAndView mv, HttpSession session) {
		Saler saler = (Saler) session.getAttribute("saler");
		if (saler == null) {
			mv.addObject("message", "销售未登录！");
			mv.setViewName("loginForm");
			return mv;
		} else {
			mv.addObject("message", "销售已验证登录成功！");
			mv.setViewName("saler");
		}

		// 获取音乐列表（只获取自身负责的类别）
		//List<Music> musicList = musicService.getAll();
		List<Music> musicList = musicService.getByCategory(saler.getSale_category());
		mv.addObject("musicList", musicList);

		// 获取对应类别的销售统计信息
		List<StatisCategoryInfo> cateInfoList = statisticService.getStatisticCategoryInfo();
		for(StatisCategoryInfo sci:cateInfoList)
		{
			if(sci.getCategory().equals(saler.getSale_category()))
			{
				mv.addObject("cateInfo", sci);
			}
		}
		
		// 获取日志记录列表
		//List<Log> logList = logService.getAllLog();
		//mv.addObject("logList", logList);
		List<CateLog> cateLogList = cateLogService.getByCategory(saler.getSale_category());
		mv.addObject("logList", cateLogList);

		return mv;
	}
	
	// 修改销售口令
	@RequestMapping(value = "/alterSalerInfo",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String alterSalerInfo(int id, String password, ModelAndView mv, HttpSession session)
	{
		if(password.equals(""))
		{
			return "{\"message\": \"销售口令不能为空！\"}";
		}
		int flag=salerService.updatePassword(password, id);
		if(flag==1)
		{
			return "{\"message\": \"销售信息修改成功！\"}";
		}
		else
		{
			return "{\"message\": \"销售信息修改失败！\"}";
		}
	}
	
	// 删除销售
	@RequestMapping(value = "/deleteSaler",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String deleteSaler(int id, ModelAndView mv, HttpSession session)
	{
		int flag=salerService.removeSaler(id);
		if(flag==1)
		{
			return "{\"message\": \"销售删除成功！\"}";
		}
		else
		{
			return "{\"message\": \"销售删除失败！\"}";
		}
	}
	
	// 添加销售
	@PostMapping(value = "/addSaler")
	public ModelAndView addSaler(String password, String sale_category, ModelAndView mv, HttpSession session)
	{
		int flag=salerService.addSaler(password, sale_category);
		if(flag==1)
		{
			mv.addObject("message", "销售添加成功！");
			mv.setView(new RedirectView("/SSMdemo/managerEnter"));
			return mv;
		}
		else
		{
			mv.addObject("message", "销售添加失败！");
			mv.setView(new RedirectView("/SSMdemo/managerEnter"));
			return mv;
		}
	}

}
