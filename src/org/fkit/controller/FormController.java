package org.fkit.controller;

import org.fkit.domain.Music;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormController {
	// ��ת��¼ҳ
	@RequestMapping(value = "/loginForm")
	public String loginForm() {
		return "/loginForm";
	}

	// ��תע��ҳ
	@RequestMapping(value = "/registerForm")
	public String registerForm() {
		return "/registerForm";
	}

	// ��ת�������ҳ
	@RequestMapping(value = "/addMusicForm")
	public String addMusicForm() {
		return "/addMusicForm";
	}
	
	// ��ת�������ҳ
	@RequestMapping(value = "/addSalerForm")
	public String addSalerForm() {
		return "/addSalerForm";
	}
}
