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

	// �û���¼
	@PostMapping(value = "/loginUser")
	public ModelAndView login(String loginname, String password, ModelAndView mv, HttpSession session) {
		User user = userService.login(loginname, password);

		if (user != null) {
			session.setAttribute("user", user);

			// �����û���¼����־��¼
			Date date = new Date();
			logService.addLog(user.getId(), "�û�(" + user.getLoginname() + ")��¼ƽ̨", date.toString());

			mv.setView(new RedirectView("/SSMdemo/index"));// ת��Servlet
		} else {
			mv.addObject("message", "��¼�������������");
			mv.setViewName("loginForm");
		}
		return mv;
	}

	// ������ҳ
	@RequestMapping(value = "/index")
	public ModelAndView index(ModelAndView mv, HttpSession session) {
		// ���������б�
		List<Music> list = musicService.getAll();
		// System.out.println(list.size());
		mv.addObject("musicList", list);

		User user = (User) session.getAttribute("user");
		if (user == null) {
			mv.addObject("message", "�û�δ��¼��");
			mv.setViewName("index");
		} else {
			mv.addObject("message", "�û�����֤��¼�ɹ���");
			mv.setViewName("index");
		}
		return mv;
	}

	// ע����¼
	@RequestMapping(value = "/logout")
	public ModelAndView logout(ModelAndView mv, HttpSession session) {
		User user = (User) session.getAttribute("user");

		session.removeAttribute("user");

		// �����û���¼����־��¼
		Date date = new Date();
		logService.addLog(user.getId(), "�û�(" + user.getLoginname() + ")ע����¼", date.toString());

		mv.addObject("message", "�û���ע����");
		mv.setView(new RedirectView("/SSMdemo/index"));// ת��Servlet
		return mv;
	}

	// �û�ע��
	@PostMapping(value = "/register")
	public ModelAndView register(String loginname, String password, ModelAndView mv, HttpSession session) {
		// System.out.println(loginname);
		// System.out.println(password);

		if (userService.checkRegister(loginname) == true) {
			// �ǳ�δ��ע��
			int flag = userService.register(loginname, password);

			if (flag == 1) {
				// ע��ɹ�
				mv.addObject("message", "�û�ע��ɹ���");
				mv.setViewName("loginForm");
				return mv;
			} else {
				// ע��ʧ��
				mv.addObject("message", "�û�ע��ʧ�ܣ�");
				mv.setViewName("registerForm");
				return mv;
			}
		} else {
			// ע��ʧ��
			mv.addObject("message", "�ǳ��ѱ�ע�ᣡ");
			mv.setViewName("registerForm");
			return mv;
		}
	}

	// ������Ϣ����ҳ
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

	// �޸��û�������Ϣ
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
				mv.addObject("message", "�û��ǳ��޸Ĳ��ɹ���");
				mv.setViewName("userInfo");
				return mv;
			}
		}
		if (!username.equals("")) {
			flag = userService.updateUsername(username, user.getId());
			if (flag != 1) {
				mv.addObject("message", "�û���ʵ�����޸Ĳ��ɹ���");
				mv.setViewName("userInfo");
				return mv;
			}
		}
		if (!phone.equals("")) {
			flag = userService.updatePhone(phone, user.getId());
			if (flag != 1) {
				mv.addObject("message", "�û��绰�޸Ĳ��ɹ���");
				mv.setViewName("userInfo");
				return mv;
			}
		}
		if (!mail.equals("")) {
			flag = userService.updateMail(mail, user.getId());
			if (flag != 1) {
				mv.addObject("message", "�û��ʼ��޸Ĳ��ɹ���");
				mv.setViewName("userInfo");
				return mv;
			}
		}
		if (!password.equals("")) {
			if (!password.equals(confirmPassword)) {
				mv.addObject("message", "�����������벻��ͬ��");
				mv.setViewName("userInfo");
				return mv;
			}
			flag = userService.updatePassword(password, user.getId());
			if (flag != 1) {
				mv.addObject("message", "�û������޸Ĳ��ɹ���");
				mv.setViewName("userInfo");
				return mv;
			}
		}

		// ����ͷ��ͼƬ�ϴ�
		if (!avatar.isEmpty()) {
			//System.out.println(avatar.getOriginalFilename());

			// �ϴ��ļ���·��
			String path = request.getServletContext().getRealPath("/IMAGES");
			String filename = avatar.getOriginalFilename();
			File filepath = new File(path, filename);
			// �ϴ�����·��
			if (!filepath.getParentFile().exists()) {
				filepath.getParentFile().mkdirs();
			}

			//System.out.println(path);
			//System.out.println(filename);

			//System.out.println("/IMAGES/" + filename);

			// ���ϴ��ļ����浽һ��Ŀ���ļ���
			try {
				avatar.transferTo(new File(path + File.separator + filename));
			} catch (IllegalStateException | IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}

			// �������ݿ���ͷ��url��ַ
			userService.updateAvatar("/IMAGES/" + filename, user.getId());

		}

		User newUser = userService.getById(user.getId());
		session.setAttribute("user", newUser);
		mv.addObject("message", "�û���Ϣ�޸ĳɹ���");
		mv.setViewName("userInfo");
		return mv;
	}
}
