package com.yogurt.controller;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

//SpringMVC拦截器，类似于Servlet中的filter
public class MyInterceptor implements HandlerInterceptor {
	/**
	 * @return  true 是放行，false是不放行
	 * Handler执行前调用
	 * 应用场景：登录认证，身份授权
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Enumeration<String> parameterNames = request.getParameterNames();
		System.out.println("URL : " + request.getRequestURL());
		while (parameterNames.hasMoreElements()){
			String paramName = parameterNames.nextElement();
			System.out.print("param : " + paramName + " = ");
			System.out.println(request.getParameter(paramName));
		}
		return true;
	}

	/**
	 * Handler处理结束，在DispatcherServlet渲染视图之前执行，此时可对ModelAndView进行处理
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 执行完Handler调用，在渲染视图完毕时调用
	 * 应用场景：统一异常处理，统一日志处理
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}
}
