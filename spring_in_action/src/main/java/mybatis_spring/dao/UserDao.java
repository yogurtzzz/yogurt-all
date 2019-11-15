package mybatis_spring.dao;

import mybatis_spring.po.User;

import java.util.List;

public interface UserDao {
	User findById(int id);
	List<User> findByName(String name);
	int insertOne(User user);
	int insertMany(List<User> users);
}
