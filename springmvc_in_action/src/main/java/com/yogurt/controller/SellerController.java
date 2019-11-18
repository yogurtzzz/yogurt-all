package com.yogurt.controller;

import com.yogurt.dao.po.Seller;
import com.yogurt.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("seller")
public class SellerController {
	@Autowired
	SellerService sellerService;

	@ResponseBody
	@RequestMapping("add")
	public String add(@RequestBody Seller seller){
		sellerService.addSeller(seller);
		return "success";
	}
}
