package mybatis_spring.mapper;


import mybatis_spring.po.User;

import java.util.List;

public interface UserMapper {
	User findById(int id);
	List<User> fuzzyFindByName(String name);
	int insertUser(User user);
	int insertBatch(List<User> users);
}
