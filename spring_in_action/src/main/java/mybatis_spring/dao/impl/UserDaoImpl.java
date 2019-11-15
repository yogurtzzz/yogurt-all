package mybatis_spring.dao.impl;

import mybatis_spring.dao.UserDao;
import mybatis_spring.mapper.UserMapper;
import mybatis_spring.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {


	@Autowired
	private UserMapper mapper;

	//编程式事务
	private TransactionTemplate transactionTemplate;

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	@Override
	public User findById(int id) {
		return mapper.findById(id);
	}

	@Override
	public List<User> findByName(String name) {
		return mapper.fuzzyFindByName(name);
	}

	@Override
	public int insertOne(User user) {
		Object effectRow = transactionTemplate.execute(new TransactionCallback<Object>() {
			@Override
			public Object doInTransaction(TransactionStatus transactionStatus) {
				return mapper.insertUser(user);
			}
		});
		return (Integer) effectRow;
	}

	@Override
	public int insertMany(List<User> users) {
		Object effectRow = transactionTemplate.execute(new TransactionCallback<Object>() {
			@Override
			public Object doInTransaction(TransactionStatus transactionStatus) {
				return mapper.insertBatch(users);
			}
		});
		return (Integer) effectRow;
	}
}
