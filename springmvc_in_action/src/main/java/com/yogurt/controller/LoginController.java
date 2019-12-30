package com.yogurt.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {

	@RequestMapping(value = "/login")
	public String login(){
		return "login";
	}

	@RequestMapping(value = "/doLogin",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String doLogin(HttpSession session,String user,String password){
		if (StringUtils.isEmpty(user) || StringUtils.isEmpty(password)){
			return "用户名和密码为空!";
		}
		System.out.println("模拟数据库操作，若验证已经成功");
		//将当前登录成功的用户保存在session中
		session.setAttribute("username",user);
		return "login success";
	}
	@RequestMapping("logout")
	@ResponseBody
	public String doLogout(HttpSession session){
		session.invalidate();
		return "session expire";
	}

	//验证是否已登录
	@RequestMapping("/validate")
	@ResponseBody
	public String validate(HttpSession session){
		String username = (String) session.getAttribute("username");
		if (StringUtils.isEmpty(username)){
			return "validate fail";
		}else{
			return "validate success";
		}
	}

	//页面跳转的几种方式
	//页面重定向和转发
	@RequestMapping("testR")
	public void testR(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		/**
		 * forward : 发生在服务端，可以共享request域，浏览器端的URL不发生变化
		 * redirect : 发生在浏览器端，浏览器URL发生变化，request域不能共享，相当于客户端另外发起一次请求
		 */

		System.out.println("ready to forward");
		//通过request，利用forward转发
		req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req,resp);

		//通过response重定向转发
		//这样是访问不了的，tomcat下的WEB-INF目录下的文件，服务端可以访问，客户端无法直接访问（出于安全考虑）
		//resp.sendRedirect("/WEB-INF/jsp/login.jsp");


		//也可以用如下方式来跳转页面
		//return "redirect:login";

		//return "forward:login";


	}

}
