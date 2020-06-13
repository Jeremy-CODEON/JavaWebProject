package org.fkit.controller;

import org.fkit.domain.Music;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormController {
	// 跳转登录页
	@RequestMapping(value = "/loginForm")
	public String loginForm() {
		return "/loginForm";
	}

	// 跳转注册页
	@RequestMapping(value = "/registerForm")
	public String registerForm() {
		return "/registerForm";
	}

	// 跳转添加音乐页
	@RequestMapping(value = "/addMusicForm")
	public String addMusicForm() {
		return "/addMusicForm";
	}
	
	// 跳转添加销售页
	@RequestMapping(value = "/addSalerForm")
	public String addSalerForm() {
		return "/addSalerForm";
	}
}
