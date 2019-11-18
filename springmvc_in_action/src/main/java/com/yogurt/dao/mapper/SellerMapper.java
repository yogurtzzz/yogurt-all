package com.yogurt.dao.mapper;


import com.yogurt.dao.po.Seller;

public interface SellerMapper {
	Seller findById(int id);
	int addOne(Seller seller);
}
