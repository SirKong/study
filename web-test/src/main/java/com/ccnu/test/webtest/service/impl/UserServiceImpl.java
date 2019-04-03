package com.ccnu.test.webtest.service.impl;

import org.springframework.stereotype.Service;

import com.ccnu.test.webtest.service.UserService;

/**
 * Created by gongyb08837 on 2016/1/25.
 */
@Service("userService")
public class UserServiceImpl implements UserService {


    @Override
    public boolean loginCheck(String username, String password) {
        return true;
    }
}
