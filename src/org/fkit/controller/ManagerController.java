package org.fkit.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.fkit.domain.Log;
import org.fkit.domain.Manager;
import org.fkit.domain.Music;
import org.fkit.domain.Saler;
import org.fkit.service.BehaviorService;
import org.fkit.service.CartService;
import org.fkit.service.LogService;
import org.fkit.service.ManagerService;
import org.fkit.service.MusicService;
import org.fkit.service.OrderService;
import org.fkit.service.SalerService;
import org.fkit.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import othersPOJO.StatisCategoryInfo;

@Controller
public class ManagerController {
	
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
	@Autowired
	@Qualifier("managerService")
	private ManagerService managerService;
	@Autowired
	@Qualifier("behaviorService")
	private BehaviorService behaviorService;
	
	//管理员登录
	@PostMapping(value = "/loginManager")
	public ModelAndView login(Integer loginid, String password, ModelAndView mv, HttpSession session) {
		Manager manager = managerService.login(loginid, password);
		if(manager != null)
		{
			session.setAttribute("manager", manager);
			mv.setView(new RedirectView("/SSMdemo/managerEnter"));// 转向Servlet
		}
		else
		{
			mv.addObject("message", "账户或者密码错误！");
			mv.setViewName("loginForm");
		}
		return mv;
	}
	
	//进入管理员页面
	@RequestMapping(value = "/managerEnter")
	public ModelAndView manage(ModelAndView mv, HttpSession session) {
		Manager manager = (Manager) session.getAttribute("manager");
		if (manager == null) {
			mv.addObject("message", "管理员未登录！");
			mv.setViewName("loginForm");
			return mv;
		} else {
			mv.addObject("message", "管理员已验证登录成功！");
			mv.setViewName("manager");
		}

		// 获取音乐列表
		List<Music> musicList = musicService.getAll();
		mv.addObject("musicList", musicList);

		// 获取类别的销售统计信息
		List<StatisCategoryInfo> cateInfoList = statisticService.getStatisticCategoryInfo();
		mv.addObject("cateInfoList", cateInfoList);

		// 获取日志记录列表
		List<Log> logList = logService.getAllLog();
		mv.addObject("logList", logList);
		
		// 获取销售人员列表
		List<Saler> salerList=salerService.getAllSaler();
		mv.addObject("salerList", salerList);
		
		ArrayList<Double> predictList=new ArrayList<Double>();
		double historyTotal=0;
		double predictTotal=0;
		for(Music m:musicList)
		{
			historyTotal+=(double)m.getPurchases();
			// 获得每个音乐对应的预测值
			double preValue=behaviorService.getPredictionValue(m.getId());
			predictList.add(preValue);
			predictTotal+=preValue;
		}
		mv.addObject("predictList", predictList);
		//System.out.println(predictList.size());
		
		ArrayList<Double> trendList=new ArrayList<Double>();
		for(int i=0;i<predictList.size();i++)
		{
			if(predictTotal!=0 && historyTotal!=0)
			{
				// 计算增长趋势
				double percent=(predictList.get(i)/predictTotal-(double)musicList.get(i).getPurchases()/historyTotal)*(double)musicList.get(i).getPurchases()/historyTotal*100;
				trendList.add(percent);
			}
			else
			{
				trendList.add((double)0);
			}
		}
		mv.addObject("trendList", trendList);
		//System.out.println(trendList.size());
		
		// 获得平均登录时长
		List<Double> browseList=managerService.getBrowseStatistic();
		mv.addObject("browseList", browseList);
		
		// 获得活跃度(群体总登录时长)
		List<Double> activeList=managerService.getActiveStatistic();
		mv.addObject("activeList", activeList);

		return mv;
	}
	
	// 管理员查看历史销量走向
	@RequestMapping(value = "/musicLineChart/{id}")
	public ModelAndView musicLineChart(@PathVariable("id") int id, ModelAndView mv, HttpSession session) {
		List<Integer> countValueList= behaviorService.getCountValue(id);
			
		mv.addObject("countValueList", countValueList);
		mv.setViewName("musicLineChart");
		return mv;
	}

}
