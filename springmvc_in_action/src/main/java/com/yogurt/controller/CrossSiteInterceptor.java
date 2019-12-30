package com.yogurt.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//跨域拦截器需要放在多个拦截器的首位
//SpringMVC 4.x 以下的版本，实现跨域的方式
public class CrossSiteInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String origin = request.getHeader("Origin");
		if (origin != null){
			//表明有跨域请求
			response.setContentType("text/html;charset=utf-8");
			response.setHeader("Access-Control-Allow-Origin",origin);
			response.setHeader("Access-Control-Allow-Methods","POST,PUT,GET,DELETE,OPTIONS");
			response.setHeader("Access-Control-Allow-Credentials","true");
			System.out.println("正在跨域");
		}
		return true;
	}
}
