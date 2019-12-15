package org.fkit.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.fkit.domain.Cart;
import org.fkit.domain.Music;
import org.fkit.domain.Order;
import org.fkit.domain.OrderMusic;
import org.fkit.domain.User;
import org.fkit.service.CartService;
import org.fkit.service.LogService;
import org.fkit.service.MusicService;
import org.fkit.service.OrderService;
import org.fkit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {
	@Autowired
	@Qualifier("userService")
	private UserService userService;
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
	@Qualifier("logService")
	private LogService logService;

	// 用户登录
	@PostMapping(value = "/loginUser")
	public ModelAndView login(String loginname, String password, ModelAndView mv, HttpSession session) {
		User user = userService.login(loginname, password);

		if (user != null) {
			session.setAttribute("user", user);

			// 插入用户登录的日志记录
			Date date = new Date();
			logService.addLog(user.getId(), "用户(" + user.getLoginname() + ")登录平台", date.toString());

			mv.setView(new RedirectView("/SSMdemo/index"));// 转向Servlet
		} else {
			mv.addObject("message", "登录名或者密码错误！");
			mv.setViewName("loginForm");
		}
		return mv;
	}

	// 进入首页
	@RequestMapping(value = "/index")
	public ModelAndView index(ModelAndView mv, HttpSession session) {
		// 读入音乐列表
		List<Music> list = musicService.getAll();
		// System.out.println(list.size());
		mv.addObject("musicList", list);

		User user = (User) session.getAttribute("user");
		if (user == null) {
			mv.addObject("message", "用户未登录！");
			mv.setViewName("index");
		} else {
			mv.addObject("message", "用户已验证登录成功！");
			mv.setViewName("index");
		}
		return mv;
	}

	// 注销登录
	@RequestMapping(value = "/logout")
	public ModelAndView logout(ModelAndView mv, HttpSession session) {
		User user = (User) session.getAttribute("user");

		session.removeAttribute("user");

		// 插入用户登录的日志记录
		Date date = new Date();
		logService.addLog(user.getId(), "用户(" + user.getLoginname() + ")注销登录", date.toString());

		mv.addObject("message", "用户已注销！");
		mv.setView(new RedirectView("/SSMdemo/index"));// 转向Servlet
		return mv;
	}

	// 用户注册
	@PostMapping(value = "/register")
	public ModelAndView register(String loginname, String password, ModelAndView mv, HttpSession session) {
		// System.out.println(loginname);
		// System.out.println(password);

		if (userService.checkRegister(loginname) == true) {
			// 昵称未被注册
			int flag = userService.register(loginname, password);

			if (flag == 1) {
				// 注册成功
				mv.addObject("message", "用户注册成功！");
				mv.setViewName("loginForm");
				return mv;
			} else {
				// 注册失败
				mv.addObject("message", "用户注册失败！");
				mv.setViewName("registerForm");
				return mv;
			}
		} else {
			// 注册失败
			mv.addObject("message", "昵称已被注册！");
			mv.setViewName("registerForm");
			return mv;
		}
	}

	// 个人信息中心页
	@RequestMapping(value = "/userInfo")
	public ModelAndView alterUserInfo(ModelAndView mv, HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<Cart> cartList = cartService.getCartInfoByUser(user.getId());
		mv.addObject("cartList", cartList);

		List<Order> orderList = orderService.getByUserId(user.getId());
		mv.addObject("orderList", orderList);

		// System.out.println(user.getCartList());
		mv.setViewName("userInfo");
		return mv;
	}

	// 修改用户个人信息
	@PostMapping(value = "/alterUserInfo")
	public ModelAndView alterUserInfo(@RequestParam(value = "loginname", required = false) String loginname,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "avatar", required = false) MultipartFile avatar,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "mail", required = false) String mail,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "confirmPassword", required = false) String confirmPassword, ModelAndView mv,
			HttpSession session, HttpServletRequest request) {
		// System.out.println(loginname);
		// System.out.println(password);

		User user = (User) session.getAttribute("user");
		int flag;
		if (!loginname.equals("")) {
			flag = userService.updateLoginname(loginname, user.getId());
			if (flag != 1) {
				mv.addObject("message", "用户昵称修改不成功！");
				mv.setViewName("userInfo");
				return mv;
			}
		}
		if (!username.equals("")) {
			flag = userService.updateUsername(username, user.getId());
			if (flag != 1) {
				mv.addObject("message", "用户真实姓名修改不成功！");
				mv.setViewName("userInfo");
				return mv;
			}
		}
		if (!phone.equals("")) {
			flag = userService.updatePhone(phone, user.getId());
			if (flag != 1) {
				mv.addObject("message", "用户电话修改不成功！");
				mv.setViewName("userInfo");
				return mv;
			}
		}
		if (!mail.equals("")) {
			flag = userService.updateMail(mail, user.getId());
			if (flag != 1) {
				mv.addObject("message", "用户邮件修改不成功！");
				mv.setViewName("userInfo");
				return mv;
			}
		}
		if (!password.equals("")) {
			if (!password.equals(confirmPassword)) {
				mv.addObject("message", "两次密码输入不相同！");
				mv.setViewName("userInfo");
				return mv;
			}
			flag = userService.updatePassword(password, user.getId());
			if (flag != 1) {
				mv.addObject("message", "用户密码修改不成功！");
				mv.setViewName("userInfo");
				return mv;
			}
		}

		// 处理头像图片上传
		if (!avatar.isEmpty()) {
			//System.out.println(avatar.getOriginalFilename());

			// 上传文件夹路径
			String path = request.getServletContext().getRealPath("/IMAGES");
			String filename = avatar.getOriginalFilename();
			File filepath = new File(path, filename);
			// 上传创建路径
			if (!filepath.getParentFile().exists()) {
				filepath.getParentFile().mkdirs();
			}

			//System.out.println(path);
			//System.out.println(filename);

			//System.out.println("/IMAGES/" + filename);

			// 将上传文件保存到一个目标文件中
			try {
				avatar.transferTo(new File(path + File.separator + filename));
			} catch (IllegalStateException | IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}

			// 更新数据库中头像url地址
			userService.updateAvatar("/IMAGES/" + filename, user.getId());

		}

		User newUser = userService.getById(user.getId());
		session.setAttribute("user", newUser);
		mv.addObject("message", "用户信息修改成功！");
		mv.setViewName("userInfo");
		return mv;
	}
}
