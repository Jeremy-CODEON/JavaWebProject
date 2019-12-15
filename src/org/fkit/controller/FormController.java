package org.fkit.controller;

import org.fkit.domain.Music;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormController {
	// Ìø×ªµÇÂ¼Ò³
	@RequestMapping(value = "/loginForm")
	public String loginForm() {
		return "/loginForm";
	}

	// Ìø×ª×¢²áÒ³
	@RequestMapping(value = "/registerForm")
	public String registerForm() {
		return "/registerForm";
	}

	// Ìø×ªÌí¼ÓÒôÀÖÒ³
	@RequestMapping(value = "/addMusicForm")
	public String addMusicForm() {
		return "/addMusicForm";
	}
}
