package org.fkit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.fkit.domain.Music;
import org.fkit.domain.User;
import org.fkit.service.CartService;
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
public class CartController {
	
	@Autowired
	@Qualifier("cartService")
	private CartService cartService;
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	//将音乐加入购物车,返回json格式
	@RequestMapping(value="/addToCart",produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String addToCart(int music_id, ModelAndView mv,HttpSession session)
	{
		//System.out.println(music_id);
		User user= (User)session.getAttribute("user");
		if(user==null)
		{
			return "{\"message\": \"该用户未登录！\"}";
		}
		else
		{
			int user_id=user.getId();
			int flag=cartService.addToCart(user_id, music_id);
			if(flag==1)
			{
				//更新页面用户信息
//				session.removeAttribute("user");
//				User newUser=userService.getById(user.getId());
//				session.setAttribute("user", newUser);
				return "{\"message\": \"购物车添加成功！\"}";
			}
			else
			{
				return "{\"message\": \"购物车添加失败！请注意是否重复添加相同音乐！\"}";
			}
		}
	}
	
	//将音乐移除购物车
	@RequestMapping(value="/removeFromCart",produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String removeFromCart(int id, ModelAndView mv,HttpSession session)
	{
		//System.out.println(id);
		User user= (User)session.getAttribute("user");
		if(user==null)
		{
			return "{\"message\": \"该用户未登录！\"}";
		}
		else
		{
			int user_id=user.getId();
			//System.out.println(user_id);
			//int flag=cartService.removeFromCartById(user_id,music_id);
			int flag=cartService.removeFromCartById(id);
			if(flag==1)
			{
				//更新页面用户信息
//				session.removeAttribute("user");
//				User newUser=userService.getById(user.getId());
//				session.setAttribute("user", newUser);
				//System.out.println("hhh"+newUser.getCartList());
				return "{\"message\": \"购物车移除成功！\"}";
			}
			else
			{
				return "{\"message\": \"购物车移除失败！\"}";
			}
		}
	}

}
