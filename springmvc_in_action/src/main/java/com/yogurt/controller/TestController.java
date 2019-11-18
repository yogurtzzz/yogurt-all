package com.yogurt.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Controller
public class TestController {
	@RequestMapping("/test")
	@ResponseBody
	public String test(HttpServletRequest req, HttpServletResponse resp){
		Enumeration headerNames = req.getHeaderNames();
		System.out.println("Header");
		while (headerNames.hasMoreElements()){
			String headerName = (String)headerNames.nextElement();
			System.out.print(headerName);
			System.out.println(" " + req.getHeader(headerName));
		}
		return "Hello";
	}
}
