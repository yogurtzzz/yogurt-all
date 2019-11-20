package com.yogurt.controller;

import com.yogurt.dao.po.Seller;
import com.yogurt.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("seller")
public class SellerController {
	@Autowired
	SellerService sellerService;

	/**
	 * 用produces 指定返回值类型，来解决乱码
	 */
	@ResponseBody
	@RequestMapping(value = "add", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String add(@RequestBody Seller seller){
		sellerService.addSeller(seller);
		return "success";
	}

	@RequestMapping("query")
	@ResponseBody
	public Seller query(Integer id){
		return sellerService.findById(id);
	}
	@RequestMapping("test")
	@ResponseBody
	public String test2(){
		System.out.println("fuck");
		return "success";
	}
}
