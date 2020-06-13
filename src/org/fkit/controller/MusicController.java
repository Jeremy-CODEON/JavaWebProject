package org.fkit.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.fkit.domain.Manager;
import org.fkit.domain.Music;
import org.fkit.domain.Saler;
import org.fkit.domain.User;
import org.fkit.service.BehaviorService;
import org.fkit.service.CateLogService;
import org.fkit.service.LogService;
import org.fkit.service.MusicService;
import org.fkit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MusicController {

	@Autowired
	@Qualifier("musicService")
	private MusicService musicService;
	@Autowired
	@Qualifier("logService")
	private LogService logService;
	@Autowired
	@Qualifier("cateLogService")
	private CateLogService cateLogService;
	@Autowired
	@Qualifier("behaviorService")
	private BehaviorService behaviorService;
	@Autowired
	@Qualifier("userService")
	private UserService userService;

	// ��������ؼ��ֲ��������б�
	@RequestMapping(value = "/searchMusicListByWord")
	public ModelAndView searchMusicListByWord(String searchInput, ModelAndView mv) {
		// ���������б�
		// System.out.println(searchInput);
		List<Music> list = musicService.getByWord(searchInput);
		// System.out.println(list.size());
		mv.addObject("musicList", list);
		mv.setViewName("index");
		return mv;
	}

	// ���������������б�
	@RequestMapping(value = "/searchMusicListByCategory1")
	public ModelAndView searchMusicListByCategory1(ModelAndView mv) {
		// ���������б�
		List<Music> list = musicService.getByCategory("������");
		// System.out.println(list.size());
		mv.addObject("musicList", list);
		mv.setViewName("index");
		return mv;
	}

	@RequestMapping(value = "/searchMusicListByCategory2")
	public ModelAndView searchMusicListByCategory2(ModelAndView mv) {
		// ���������б�
		List<Music> list = musicService.getByCategory("ŷ����ҥ");
		// System.out.println(list.size());
		mv.addObject("musicList", list);
		mv.setViewName("index");
		return mv;
	}

	@RequestMapping(value = "/searchMusicListByCategory3")
	public ModelAndView searchMusicListByCategory3(ModelAndView mv) {
		// ���������б�
		List<Music> list = musicService.getByCategory("����");
		// System.out.println(list.size());
		mv.addObject("musicList", list);
		mv.setViewName("index");
		return mv;
	}

	// �û��鿴����������
	@RequestMapping(value = "/musicDetail/{id}")
	public ModelAndView musicDetailController(@PathVariable("id") int id, ModelAndView mv, HttpSession session) {
		Music music = musicService.getById(id);
		
		// �����û�������ֵ���־��¼
		User user = (User) session.getAttribute("user");
		Date date = new Date();
		Calendar cal=Calendar.getInstance();
		if (user != null) {
			//Ϊ����Ա��ӵ���־
			logService.addLog(user.getId(), "�û�("+user.getLoginname()+")�鿴-->����id:" + id+"-"+music.getName(), date.toString());
			//Ϊ������ӵ���־
			cateLogService.addCateLog(user.getId(), music.getCategory(), "�û�("+user.getLoginname()+")�鿴-->����id:" + id+"-"+music.getName(), date.toString());
			//����͹����¼
			behaviorService.addBehavior(user.getId(), music.getId(), 1, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DATE));
			//�����û�������Ϣ
			userService.classifyUser(user.getId(), user.getAge());
		}

		mv.addObject("music", music);
		mv.setViewName("musicDetail");
		return mv;
	}

	// ���۲鿴���޸ĵ���������
	@RequestMapping(value = "/musicInfoForSaler/{id}")
	public ModelAndView musicInfoForSaler(@PathVariable("id") int id, ModelAndView mv, HttpSession session) {
		Music music = musicService.getById(id);
		
		// ��ȡ����
		Date date = new Date();
		Saler saler = (Saler) session.getAttribute("saler");
		if(saler!=null)
		{
			//��¼������־
			logService.addLog(990000+saler.getId(), "����(���:" + saler.getId() + ")�鿴����"+music.getName()+"(id:"+music.getId()+")", date.toString());
		}
		else
		{
			Manager manager = (Manager) session.getAttribute("manager");
			//��¼������־
			logService.addLog(880000+manager.getId(), "����Ա(���:" + manager.getId() + ")�鿴����"+music.getName()+"(id:"+music.getId()+")", date.toString());
		}
		
		mv.addObject("music", music);
		mv.setViewName("musicInfoForSaler");
		return mv;
	}

	// �޸�������Ϣ
	@RequestMapping(value = "/alterMusicInfo")
	public ModelAndView alterUserInfo(Integer id, String name, String singer, String description, String album,
			String duration, String category, Double cost, Integer state, Integer purchases, ModelAndView mv,
			HttpSession session) {
		//System.out.println(id);

		int flag;
		if (!name.equals("")) {
			flag = musicService.updateName(name, id);
			if (flag != 1) {
				mv.addObject("message", "�������޸Ĳ��ɹ���");
				mv.setView(new RedirectView("/SSMdemo/musicInfoForSaler/"+id));// ת��Servlet
				return mv;
			}
		}
		if (!singer.equals("")) {
			flag = musicService.updateSinger(singer, id);
			if (flag != 1) {
				mv.addObject("message", "���ָ����޸Ĳ��ɹ���");
				mv.setView(new RedirectView("/SSMdemo/musicInfoForSaler/"+id));// ת��Servlet
				return mv;
			}
		}
		if (!description.equals("")) {
			flag = musicService.updateDescription(description, id);
			if (flag != 1) {
				mv.addObject("message", "���������޸Ĳ��ɹ���");
				mv.setView(new RedirectView("/SSMdemo/musicInfoForSaler/"+id));// ת��Servlet
				return mv;
			}
		}
		if (!album.equals("")) {
			flag = musicService.updateAlbum(album, id);
			if (flag != 1) {
				mv.addObject("message", "����ר���޸Ĳ��ɹ���");
				mv.setView(new RedirectView("/SSMdemo/musicInfoForSaler/"+id));// ת��Servlet
				return mv;
			}
		}
		if (!duration.equals("")) {
			flag = musicService.updateDuration(duration, id);
			if (flag != 1) {
				mv.addObject("message", "����ʱ���޸Ĳ��ɹ���");
				mv.setView(new RedirectView("/SSMdemo/musicInfoForSaler/"+id));// ת��Servlet
				return mv;
			}
		}
		if (!category.equals("")) {
			flag = musicService.updateCategory(category, id);
			if (flag != 1) {
				mv.addObject("message", "��������޸Ĳ��ɹ���");
				mv.setView(new RedirectView("/SSMdemo/musicInfoForSaler/"+id));// ת��Servlet
				return mv;
			}
		}
		if (cost != null) {
			flag = musicService.updateCost(cost, id);
			if (flag != 1) {
				mv.addObject("message", "���ֵ����޸Ĳ��ɹ���");
				mv.setView(new RedirectView("/SSMdemo/musicInfoForSaler/"+id));// ת��Servlet
				return mv;
			}
		}
		if (state != null) {
			if(state!=0&&state!=1)
			{
				mv.addObject("message", "����״̬��������");
				mv.setView(new RedirectView("/SSMdemo/musicInfoForSaler/"+id));// ת��Servlet
				return mv;
			}
			flag = musicService.updateState(state, id);
			if (flag != 1) {
				mv.addObject("message", "����״̬�޸Ĳ��ɹ���");
				mv.setView(new RedirectView("/SSMdemo/musicInfoForSaler/"+id));// ת��Servlet
				return mv;
			}
		}
		
		// ��ȡ����
		Date date = new Date();
		
		Saler saler = (Saler) session.getAttribute("saler");
		if(saler!=null)
		{
			// ��¼������־
		    logService.addLog(990000+saler.getId(), "����(���:" + saler.getId() + ")�޸�����(id:"+id+")", date.toString());
		}
		else
		{
			Manager manager = (Manager) session.getAttribute("manager");
			if(manager!=null)
			{
				// ��¼����Ա��־
			    logService.addLog(880000+manager.getId(), "����Ա(���:" + manager.getId() + ")�޸�����(id:"+id+")", date.toString());
			}
		}
	    
		mv.addObject("message", "������Ϣ�޸ĳɹ���");
		mv.setView(new RedirectView("/SSMdemo/musicInfoForSaler/"+id));// ת��Servlet
		return mv;
	}

	// �������
	@PostMapping(value = "/addMusic")
	public ModelAndView addMusic(String name, String singer, String description, String album, String duration,
			String category, double cost, int state, ModelAndView mv, HttpSession session) 
	{
		int flag = musicService.addMusic(name, singer, description, album, duration, category, cost, state);
		if (flag == 1) 
		{
			// ��ȡ����
			Date date = new Date();
			Saler saler = (Saler) session.getAttribute("saler");	
			if(saler!=null)
			{
				//��¼������־
				logService.addLog(990000+saler.getId(), "����(���:" + saler.getId() + ")�������:"+name+"(״̬:�ɹ�)", date.toString());
			}
			else
			{
				Manager manager = (Manager) session.getAttribute("manager");
				if(manager!=null)
				{
					// ��¼����Ա��־
				    logService.addLog(880000+manager.getId(), "����Ա(���:" + manager.getId() + ")�������:"+name+"(״̬:�ɹ�)", date.toString());	
				}
			}
			
			mv.addObject("message", "������ӳɹ���");
			mv.setView(new RedirectView("/SSMdemo/salerEnter"));
			return mv;
		} 
		else 
		{	
			// ��ȡ����
			Date date = new Date();
			Saler saler = (Saler) session.getAttribute("saler");
			if(saler!=null)
			{
				//��¼������־
				logService.addLog(990000+saler.getId(), "����(���:" + saler.getId() + ")�������:"+name+"(״̬:ʧ��)", date.toString());			
			}			
			else
			{
				Manager manager = (Manager) session.getAttribute("manager");
				if(manager!=null)
				{
					// ��¼����Ա��־
				    logService.addLog(880000+manager.getId(), "����Ա(���:" + manager.getId() + ")�������:"+name+"(״̬:ʧ��)", date.toString());	
				}
			}
			
			mv.addObject("message", "�������ʧ�ܣ�");
			mv.setView(new RedirectView("/SSMdemo/salerEnter"));
			return mv;
		}
	}

	// ɾ������
	@RequestMapping(value="/deleteMusic",produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String deleteMusic(int id, ModelAndView mv,HttpSession session)
	{
		int flag = musicService.deleteMusic(id);
		if (flag == 1) 
		{
			// ��ȡ����
			Date date = new Date();
			Saler saler = (Saler) session.getAttribute("saler");	
			if(saler!=null)
			{
				//��¼������־
				logService.addLog(990000+saler.getId(), "����(���:" + saler.getId() + ")ɾ�����֣�id="+id+"(״̬:�ɹ�)", date.toString());
			}
			
			return "{\"message\": \"����ɾ���ɹ���\"}";
		} 
		else 
		{
			// ��ȡ����
			Date date = new Date();
			Saler saler = (Saler) session.getAttribute("saler");	
			if(saler!=null)
			{
				//��¼������־
				logService.addLog(990000+saler.getId(), "����(���:" + saler.getId() + ")ɾ�����֣�id="+id+"(״̬:ʧ��)", date.toString());
			}
			return "{\"message\": \"����ɾ��ʧ�ܣ�\"}";
		}
	}

}
