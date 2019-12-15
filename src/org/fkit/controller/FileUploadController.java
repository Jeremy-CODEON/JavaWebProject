package org.fkit.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class FileUploadController {
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@PostMapping(value="/uploadAvatar")
	public void uploadAvatar(HttpServletRequest request,HttpSession session, @RequestParam("file")MultipartFile avatar) throws Exception
	{		
		System.out.println("hhh");
		
		User user = (User) session.getAttribute("user");
		
		System.out.println(user);
		
		if(user==null)
		{
			return;
		}
		
		if(!avatar.isEmpty())
		{
			//上传文件夹路径
			String path=request.getServletContext().getRealPath("/IMAGES");
			String filename=avatar.getOriginalFilename();
			File filepath=new File(path,filename);
			//上传创建路径
			if(!filepath.getParentFile().exists())
			{
				filepath.getParentFile().mkdirs();
			}
			System.out.println(path+File.separator+filename);
			//将上传文件保存到一个目标文件中
			avatar.transferTo(new File(path+File.separator+filename));
			
			//更新数据库中头像url地址
			userService.updateAvatar(path+File.separator+filename, user.getId());
			
            return;
		}
		else
		{
			return;
		}
	}
}
