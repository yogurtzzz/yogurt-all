package mybatis_spring.mapper;

import mybatis_spring.po.Seller;

public interface SellerMapper {
	Seller findById(int id);
	int addOne(Seller seller);
}
