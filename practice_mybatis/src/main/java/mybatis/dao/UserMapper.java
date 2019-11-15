package mybatis.dao;

import mybatis.dto.QueryVo;
import mybatis.po.BuyerExt;
import mybatis.po.OrderExt;
import mybatis.po.User;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface UserMapper {
	User findUserById(int id);
	void insertUser(User user);
	void updateUser(User user);
	void deleteUserById(int id);
	List<User> findByMultiCondition(QueryVo queryVo);
	List<User> findByMap(@Param("name") String name,@Param("gender") Integer gender);
	OrderExt findOrderAndBuyer(int orderId);
	BuyerExt findBuyerAndOrder(int buyerId);
	BuyerExt findUserAndOrdersLazy(int buyerId);
	List<User> findSelective(QueryVo queryVo);
	List<User> find4Each(QueryVo queryVo);
	User findByName(String name);
}
