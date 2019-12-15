package org.fkit.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.fkit.domain.Music;
import org.fkit.domain.User;
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
		if (user != null) {
			logService.addLog(user.getId(), "�û�("+user.getLoginname()+")�鿴-->����id:" + id+"-"+music.getName(), date.toString());
		}


		mv.addObject("music", music);
		mv.setViewName("musicDetail");
		return mv;
	}

	// ���۲鿴���޸ĵ���������
	@RequestMapping(value = "/musicInfoForSaler/{id}")
	public ModelAndView musicInfoForSaler(@PathVariable("id") int id, ModelAndView mv) {
		Music music = musicService.getById(id);
		mv.addObject("music", music);
		mv.setViewName("musicInfoForSaler");
		return mv;
	}

	// �޸�������Ϣ
	@RequestMapping(value = "/alterMusicInfo")
	public ModelAndView alterUserInfo(Integer id, String name, String singer, String description, String album,
			String duration, String category, Double cost, Integer state, Integer purchases, ModelAndView mv,
			HttpSession session) {
		System.out.println(id);

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
		mv.addObject("message", "������Ϣ�޸ĳɹ���");
		mv.setView(new RedirectView("/SSMdemo/musicInfoForSaler/"+id));// ת��Servlet
		return mv;
	}

	// �������
	@PostMapping(value = "/addMusic")
	public ModelAndView addMusic(String name, String singer, String description, String album, String duration,
			String category, double cost, int state, ModelAndView mv) {
		int flag = musicService.addMusic(name, singer, description, album, duration, category, cost, state);
		if (flag == 1) {
			mv.addObject("message", "������ӳɹ���");
			mv.setView(new RedirectView("/SSMdemo/manage"));
			return mv;
		} else {
			mv.addObject("message", "�������ʧ�ܣ�");
			mv.setView(new RedirectView("/SSMdemo/manage"));
			return mv;
		}
	}

	// �������
	@RequestMapping(value="/deleteMusic",produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String deleteMusic(int id, ModelAndView mv,HttpSession session)
	{
		int flag = musicService.deleteMusic(id);
		if (flag == 1) {
			return "{\"message\": \"����ɾ���ɹ���\"}";
		} else {
			return "{\"message\": \"����ɾ��ʧ�ܣ�\"}";
		}
	}

}
