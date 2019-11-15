package mybatis_spring.mapper;

import mybatis_spring.po.Order;

import java.util.List;

public interface OrderMapper {
	Order findById(int id);
	List<Order> findByProduct(String productName);
	int addOrder(Order order);
	int addBatch(List<Order> orders);
}
