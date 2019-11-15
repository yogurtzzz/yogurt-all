package mybatis_spring.dao.impl;

import mybatis_spring.dao.SellerDao;
import mybatis_spring.mapper.SellerMapper;
import mybatis_spring.po.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

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
