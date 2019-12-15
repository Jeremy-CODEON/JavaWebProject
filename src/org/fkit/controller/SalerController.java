package org.fkit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.fkit.domain.Cart;
import org.fkit.domain.Log;
import org.fkit.domain.Music;
import org.fkit.domain.Order;
import org.fkit.domain.OrderMusic;
import org.fkit.domain.Saler;
import org.fkit.domain.User;
import org.fkit.service.CartService;
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
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
	@Qualifier("logService")
	private LogService logService;

	// ���۵�¼
	@PostMapping(value = "/loginSaler")
	public ModelAndView login(Integer loginid, String password, ModelAndView mv, HttpSession session) {
		Saler saler = salerService.login(loginid, password);
		// System.out.println(loginid);
		// System.out.println(password);
		if (saler != null) {
			session.setAttribute("saler", saler);
			mv.setView(new RedirectView("/SSMdemo/manage"));// ת��Servlet
		} else {
			mv.addObject("message", "�˻������������");
			mv.setViewName("loginForm");
		}
		return mv;
	}

	// �������ҳ��
	@RequestMapping(value = "/manage")
	public ModelAndView manage(ModelAndView mv, HttpSession session) {
		Saler saler = (Saler) session.getAttribute("saler");
		if (saler == null) {
			mv.addObject("message", "����δ��¼��");
			mv.setViewName("loginForm");
			return mv;
		} else {
			mv.addObject("message", "��������֤��¼�ɹ���");
			mv.setViewName("manage");
		}

		// ��ȡ�����б�
		List<Music> musicList = musicService.getAll();
		mv.addObject("musicList", musicList);

		// ��ȡ��������ͳ����Ϣ
		List<StatisCategoryInfo> cateInfoList = statisticService.getStatisticCategoryInfo();
		mv.addObject("cateInfoList", cateInfoList);

		// ��ȡ��־��¼�б�
		List<Log> logList = logService.getAllLog();
		mv.addObject("logList", logList);

		return mv;
	}

}
