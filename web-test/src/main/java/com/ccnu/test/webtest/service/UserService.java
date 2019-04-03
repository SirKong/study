package com.ccnu.test.webtest.service;

/**
 * Created by gongyb08837 on 2016/1/25.
 */

public interface UserService {

    /**
     * 用户登录检查
     *
     * @param username
     * @param password
     * @return
     */
    boolean loginCheck(String username, String password);
}
