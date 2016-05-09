package com.ccnu.test.mybatis.service.impl;

import com.ccnu.test.mybatis.dao.UserDao;
import com.ccnu.test.mybatis.model.User;
import com.ccnu.test.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gongyb08837 on 2016/1/23.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	/**
	 * 使用@Autowired注解标注userDao变量， 当需要使用userDao时，Spring就会自动注入userDao
	 */
	@Autowired
	private UserDao userDao;// 注入dao

	@Override
	public void addUser(User user) {
		userDao.insert(user);
	}

	@Override
	public User getUserById(String userId) {
		return userDao.selectByPrimaryKey(userId);
	}
}
