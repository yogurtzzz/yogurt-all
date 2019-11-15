package mybatis_spring.dao;

import mybatis_spring.po.Seller;

public interface SellerDao {
	Seller findById(int id);
	int addOne(Seller seller);
}
