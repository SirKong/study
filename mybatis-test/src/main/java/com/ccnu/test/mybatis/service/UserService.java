package com.ccnu.test.mybatis.service;

import com.ccnu.test.mybatis.model.User;

/**
 * Created by gongyb08837 on 2016/1/23.
 */
public interface UserService {
    /**
     * 添加用户
     *
     * @param user
     */
    void addUser(User user);

    /**
     * 根据用户id获取用户
     *
     * @param userId
     * @return
     */
    User getUserById(String userId);
}
