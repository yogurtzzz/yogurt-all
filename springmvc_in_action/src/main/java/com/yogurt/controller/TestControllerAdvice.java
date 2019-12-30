package com.yogurt.controller;


import com.yogurt.dao.po.Seller;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

/**
 * 该注解声明了一个增强器，会对所有@Controller的类进行增强
 * 该类内部可以使用
 * @InitBinder
 * @ExceptionHandler
 * 来注解方法
 * 这些被注解的方法会被应用到所有@RequestMapping的方法上
 *
 *
 * @InitBinder  和 Converter 类似，用于http请求参数的 绑定
 * 通过PropertyEditor来解决数据类型转换的问题
 * InitBinder只能实现String - > 任意类型 的转换
 * Converter 可以实现任意类型 - > 任意类型 的转换
 */
@ControllerAdvice
public class TestControllerAdvice {

	@InitBinder
	public void initBind(WebDataBinder binder){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat,true));
		System.out.println("Init Date Binder");
	}

	@ExceptionHandler(value = IOException.class)
	public String handleException(Exception ex, HttpServletRequest req, HttpServletResponse resp,Model model){
		Enumeration<String> headerNames = req.getHeaderNames();
		while (headerNames.hasMoreElements()){
			String s = headerNames.nextElement();
			System.out.print(s + " : " + req.getHeader(s));
			System.out.println();
		}
		if (ex instanceof FileNotFoundException){
			System.out.println("file not found");
		}else if (ex instanceof EOFException){
			System.out.println("EOF");
		}else if (ex instanceof UnsupportedEncodingException){
			System.out.println("unsupport encoding");
		}
		model.addAttribute("errorMsg",ex.getMessage());
		return "error";
	}
}
