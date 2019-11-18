package com.yogurt.dao;


import com.yogurt.dao.po.Seller;

public interface SellerDao {
	Seller findById(int id);
	int addOne(Seller seller);
}
