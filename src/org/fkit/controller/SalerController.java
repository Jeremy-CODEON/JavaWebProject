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

	// ���۵�¼
	@PostMapping(value = "/loginSaler")
	public ModelAndView login(Integer loginid, String password, ModelAndView mv, HttpSession session) {
		Saler saler = salerService.login(loginid, password);
		// System.out.println(loginid);
		// System.out.println(password);
		if (saler != null) 
		{
			// ��ȡ���ۿͻ���ip
			String ip=IPUtil.getIP(req);
			// ��ȡ����
			Date date = new Date();
			//��¼������־
			logService.addLog(990000+saler.getId(), "����(��ţ�" + saler.getId() + ")��¼ƽ̨��IP="+ip, date.toString());
			
			session.setAttribute("saler", saler);
			mv.setView(new RedirectView("/SSMdemo/salerEnter"));// ת��Servlet
		} 
		else 
		{
			mv.addObject("message", "�˻������������");
			mv.setViewName("loginForm");
		}
		return mv;
	}

	// �������۹���ҳ��
	@RequestMapping(value = "/salerEnter")
	public ModelAndView manage(ModelAndView mv, HttpSession session) {
		Saler saler = (Saler) session.getAttribute("saler");
		if (saler == null) {
			mv.addObject("message", "����δ��¼��");
			mv.setViewName("loginForm");
			return mv;
		} else {
			mv.addObject("message", "��������֤��¼�ɹ���");
			mv.setViewName("saler");
		}

		// ��ȡ�����б�ֻ��ȡ����������
		//List<Music> musicList = musicService.getAll();
		List<Music> musicList = musicService.getByCategory(saler.getSale_category());
		mv.addObject("musicList", musicList);

		// ��ȡ��Ӧ��������ͳ����Ϣ
		List<StatisCategoryInfo> cateInfoList = statisticService.getStatisticCategoryInfo();
		for(StatisCategoryInfo sci:cateInfoList)
		{
			if(sci.getCategory().equals(saler.getSale_category()))
			{
				mv.addObject("cateInfo", sci);
			}
		}
		
		// ��ȡ��־��¼�б�
		//List<Log> logList = logService.getAllLog();
		//mv.addObject("logList", logList);
		List<CateLog> cateLogList = cateLogService.getByCategory(saler.getSale_category());
		mv.addObject("logList", cateLogList);

		return mv;
	}
	
	// �޸����ۿ���
	@RequestMapping(value = "/alterSalerInfo",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String alterSalerInfo(int id, String password, ModelAndView mv, HttpSession session)
	{
		if(password.equals(""))
		{
			return "{\"message\": \"���ۿ����Ϊ�գ�\"}";
		}
		int flag=salerService.updatePassword(password, id);
		if(flag==1)
		{
			return "{\"message\": \"������Ϣ�޸ĳɹ���\"}";
		}
		else
		{
			return "{\"message\": \"������Ϣ�޸�ʧ�ܣ�\"}";
		}
	}
	
	// ɾ������
	@RequestMapping(value = "/deleteSaler",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String deleteSaler(int id, ModelAndView mv, HttpSession session)
	{
		int flag=salerService.removeSaler(id);
		if(flag==1)
		{
			return "{\"message\": \"����ɾ���ɹ���\"}";
		}
		else
		{
			return "{\"message\": \"����ɾ��ʧ�ܣ�\"}";
		}
	}
	
	// �������
	@PostMapping(value = "/addSaler")
	public ModelAndView addSaler(String password, String sale_category, ModelAndView mv, HttpSession session)
	{
		int flag=salerService.addSaler(password, sale_category);
		if(flag==1)
		{
			mv.addObject("message", "������ӳɹ���");
			mv.setView(new RedirectView("/SSMdemo/managerEnter"));
			return mv;
		}
		else
		{
			mv.addObject("message", "�������ʧ�ܣ�");
			mv.setView(new RedirectView("/SSMdemo/managerEnter"));
			return mv;
		}
	}

}
