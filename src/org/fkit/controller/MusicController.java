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

	// 按照输入关键字查找音乐列表
	@RequestMapping(value = "/searchMusicListByWord")
	public ModelAndView searchMusicListByWord(String searchInput, ModelAndView mv) {
		// 读入音乐列表
		// System.out.println(searchInput);
		List<Music> list = musicService.getByWord(searchInput);
		// System.out.println(list.size());
		mv.addObject("musicList", list);
		mv.setViewName("index");
		return mv;
	}

	// 按照类别查找音乐列表
	@RequestMapping(value = "/searchMusicListByCategory1")
	public ModelAndView searchMusicListByCategory1(ModelAndView mv) {
		// 读入音乐列表
		List<Music> list = musicService.getByCategory("纯音乐");
		// System.out.println(list.size());
		mv.addObject("musicList", list);
		mv.setViewName("index");
		return mv;
	}

	@RequestMapping(value = "/searchMusicListByCategory2")
	public ModelAndView searchMusicListByCategory2(ModelAndView mv) {
		// 读入音乐列表
		List<Music> list = musicService.getByCategory("欧美民谣");
		// System.out.println(list.size());
		mv.addObject("musicList", list);
		mv.setViewName("index");
		return mv;
	}

	@RequestMapping(value = "/searchMusicListByCategory3")
	public ModelAndView searchMusicListByCategory3(ModelAndView mv) {
		// 读入音乐列表
		List<Music> list = musicService.getByCategory("国语");
		// System.out.println(list.size());
		mv.addObject("musicList", list);
		mv.setViewName("index");
		return mv;
	}

	// 用户查看的音乐详情
	@RequestMapping(value = "/musicDetail/{id}")
	public ModelAndView musicDetailController(@PathVariable("id") int id, ModelAndView mv, HttpSession session) {
		Music music = musicService.getById(id);
		
		// 插入用户浏览音乐的日志记录
		User user = (User) session.getAttribute("user");
		Date date = new Date();
		if (user != null) {
			logService.addLog(user.getId(), "用户("+user.getLoginname()+")查看-->音乐id:" + id+"-"+music.getName(), date.toString());
		}


		mv.addObject("music", music);
		mv.setViewName("musicDetail");
		return mv;
	}

	// 销售查看和修改的音乐详情
	@RequestMapping(value = "/musicInfoForSaler/{id}")
	public ModelAndView musicInfoForSaler(@PathVariable("id") int id, ModelAndView mv) {
		Music music = musicService.getById(id);
		mv.addObject("music", music);
		mv.setViewName("musicInfoForSaler");
		return mv;
	}

	// 修改音乐信息
	@RequestMapping(value = "/alterMusicInfo")
	public ModelAndView alterUserInfo(Integer id, String name, String singer, String description, String album,
			String duration, String category, Double cost, Integer state, Integer purchases, ModelAndView mv,
			HttpSession session) {
		System.out.println(id);

		int flag;
		if (!name.equals("")) {
			flag = musicService.updateName(name, id);
			if (flag != 1) {
				mv.addObject("message", "音乐名修改不成功！");
				mv.setView(new RedirectView("/SSMdemo/musicInfoForSaler/"+id));// 转向Servlet
				return mv;
			}
		}
		if (!singer.equals("")) {
			flag = musicService.updateSinger(singer, id);
			if (flag != 1) {
				mv.addObject("message", "音乐歌手修改不成功！");
				mv.setView(new RedirectView("/SSMdemo/musicInfoForSaler/"+id));// 转向Servlet
				return mv;
			}
		}
		if (!description.equals("")) {
			flag = musicService.updateDescription(description, id);
			if (flag != 1) {
				mv.addObject("message", "音乐描述修改不成功！");
				mv.setView(new RedirectView("/SSMdemo/musicInfoForSaler/"+id));// 转向Servlet
				return mv;
			}
		}
		if (!album.equals("")) {
			flag = musicService.updateAlbum(album, id);
			if (flag != 1) {
				mv.addObject("message", "音乐专辑修改不成功！");
				mv.setView(new RedirectView("/SSMdemo/musicInfoForSaler/"+id));// 转向Servlet
				return mv;
			}
		}
		if (!duration.equals("")) {
			flag = musicService.updateDuration(duration, id);
			if (flag != 1) {
				mv.addObject("message", "音乐时长修改不成功！");
				mv.setView(new RedirectView("/SSMdemo/musicInfoForSaler/"+id));// 转向Servlet
				return mv;
			}
		}
		if (!category.equals("")) {
			flag = musicService.updateCategory(category, id);
			if (flag != 1) {
				mv.addObject("message", "音乐类别修改不成功！");
				mv.setView(new RedirectView("/SSMdemo/musicInfoForSaler/"+id));// 转向Servlet
				return mv;
			}
		}
		if (cost != null) {
			flag = musicService.updateCost(cost, id);
			if (flag != 1) {
				mv.addObject("message", "音乐单价修改不成功！");
				mv.setView(new RedirectView("/SSMdemo/musicInfoForSaler/"+id));// 转向Servlet
				return mv;
			}
		}
		if (state != null) {
			if(state!=0&&state!=1)
			{
				mv.addObject("message", "音乐状态输入有误！");
				mv.setView(new RedirectView("/SSMdemo/musicInfoForSaler/"+id));// 转向Servlet
				return mv;
			}
			flag = musicService.updateState(state, id);
			if (flag != 1) {
				mv.addObject("message", "音乐状态修改不成功！");
				mv.setView(new RedirectView("/SSMdemo/musicInfoForSaler/"+id));// 转向Servlet
				return mv;
			}
		}
		mv.addObject("message", "音乐信息修改成功！");
		mv.setView(new RedirectView("/SSMdemo/musicInfoForSaler/"+id));// 转向Servlet
		return mv;
	}

	// 添加音乐
	@PostMapping(value = "/addMusic")
	public ModelAndView addMusic(String name, String singer, String description, String album, String duration,
			String category, double cost, int state, ModelAndView mv) {
		int flag = musicService.addMusic(name, singer, description, album, duration, category, cost, state);
		if (flag == 1) {
			mv.addObject("message", "音乐添加成功！");
			mv.setView(new RedirectView("/SSMdemo/manage"));
			return mv;
		} else {
			mv.addObject("message", "音乐添加失败！");
			mv.setView(new RedirectView("/SSMdemo/manage"));
			return mv;
		}
	}

	// 添加音乐
	@RequestMapping(value="/deleteMusic",produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String deleteMusic(int id, ModelAndView mv,HttpSession session)
	{
		int flag = musicService.deleteMusic(id);
		if (flag == 1) {
			return "{\"message\": \"音乐删除成功！\"}";
		} else {
			return "{\"message\": \"音乐删除失败！\"}";
		}
	}

}
