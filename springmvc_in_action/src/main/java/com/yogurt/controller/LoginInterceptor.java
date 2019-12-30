package com.yogurt.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requestURI = request.getRequestURI();
		System.out.println(requestURI);
		//是执行登录操作，放行
		if (requestURI.toLowerCase().contains("login"))
			return true;
		String user = (String) request.getSession().getAttribute("username");
		if (StringUtils.isEmpty(user)){
			System.out.println("拦截器 : 用户登录已失效");
			response.sendRedirect("/login");
			return false;
		}
		else{
			System.out.println("拦截器 : 用户已登录 : " + user);
			return true;
		}
	}
}
