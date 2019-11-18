package com.yogurt.controller;


import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.Enumeration;

@Controller
public class TestController {

	@RequestMapping("testJsp")
	public String jsp(Model model){
		model.addAttribute("name","yogurt");
		model.addAttribute("content","hello mother fucker");
		return "test";
	}
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

	@RequestMapping("/test/{page}")
	@ResponseBody
	public void testPage(@PathVariable("page")int page,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		System.out.println("page " + page);
		//使用request转发页面
		request.getRequestDispatcher("/testJsp").forward(request,response);
		//使用response重定向页面
		//response.sendRedirect("test");
		//使用response指定响应结果
		//response.setCharacterEncoding("utf-8");
		//response.setContentType("application/json;charset=utf-8");
		//response.getWriter().write("哈哈哈");
	}

	//测试参数绑定
	//简单参数绑定，要求http请求中参数的key与形参名一致
	//参数名不一致，则使用@RequestParam
	@ResponseBody
	@RequestMapping(value = "/test/bind")
	public String testParamBinding(String name,
	                             @RequestParam("addr")String address,
	                             Integer[] id,
	                             QueryVo vo,
	                               HttpServletRequest request) throws UnsupportedEncodingException {
		String addr = request.getParameter("addr");
		//下面是对中文乱码的终极解决方案
		//String utf8addr = new String(addr.getBytes("iso-8859-1"),"utf-8");
		System.out.println(name);
		System.out.println(address);
		for (Integer item: id){
			System.out.print(item + " ");
		}
		//这还乱码？
		System.out.println("老子是谁");
		return "success";
	}

	@RequestMapping("testEncoding")
	@ResponseBody
	public String fuck(){
		System.out.println("老子是谁");
		return "success";
	}

	@RequestMapping("testDate")
	@ResponseBody
	public String testDate(Date date){
		System.out.println(date);
		return "success";
	}

	@RequestMapping("uploadFile")
	@ResponseBody
	public String testUpload(MultipartFile file,HttpServletRequest req) throws IOException {
		if (file == null)
			return "File is null";
		String savePath = req.getSession().getServletContext().getRealPath("/") + "filesReceived\\";
		String fileName = file.getName(); //这一句是获取了MultipartFile的参数名称（在http请求中的入参的key）
		String originalName = file.getOriginalFilename();
		String fileType = originalName.substring(originalName.lastIndexOf("."));

		File dir = new File(savePath);
		if (!dir.exists())
			dir.mkdirs();

		//若不存在路径，需要实现创建好文件夹目录

		File storeFile = new File(savePath + originalName);
		if (fileType.equals("pdf")){
			//这一句会直接将文件内容输出到storeFile
			file.transferTo(storeFile);
		}
		return "success";
	}
	@RequestMapping("upload")
	public String testUpload() {
		return "upload";
	}

}
