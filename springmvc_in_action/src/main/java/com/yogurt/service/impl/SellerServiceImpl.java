package com.yogurt.service.impl;

import com.yogurt.dao.SellerDao;
import com.yogurt.dao.po.Seller;
import com.yogurt.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

	@Autowired
	SellerDao sellerDao;

	@Override
	public int addSeller(Seller seller) {
		return sellerDao.addOne(seller);
	}
}
