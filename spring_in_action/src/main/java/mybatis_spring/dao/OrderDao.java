package mybatis_spring.dao;

import mybatis_spring.po.Order;

import java.util.List;

public interface OrderDao {
	Order findById(int id);
	List<Order> findByName(String name);
	int addOne(Order order);
}
