package com.ccnu.test.mybatis.dao;

import com.ccnu.test.mybatis.model.User;

public interface UserDao {
	int deleteByPrimaryKey(String userId);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(String userId);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);
}