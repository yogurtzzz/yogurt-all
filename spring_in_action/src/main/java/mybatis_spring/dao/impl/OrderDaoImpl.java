package mybatis_spring.dao.impl;

import mybatis_spring.dao.OrderDao;
import mybatis_spring.mapper.OrderMapper;
import mybatis_spring.po.Order;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
	//声明式事务
	@Autowired
	OrderMapper mapper;
	@Override
	public Order findById(int id) {
		return mapper.findById(id);
	}

	@Override
	public List<Order> findByName(String name) {
		return mapper.findByProduct(name);
	}

	@Override
	public int addOne(Order order) {
		mapper.addOrder(order);
		throw new RuntimeException("hha");
	}
}
