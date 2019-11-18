package com.yogurt.dao.impl;


import com.yogurt.dao.SellerDao;
import com.yogurt.dao.mapper.SellerMapper;
import com.yogurt.dao.po.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class SellerDaoImpl implements SellerDao {

	//注解式事务

	@Autowired
	private SellerMapper mapper;

	@Override
	public Seller findById(int id) {
		return mapper.findById(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public int addOne(Seller seller) {
		return mapper.addOne(seller);
	}
}
