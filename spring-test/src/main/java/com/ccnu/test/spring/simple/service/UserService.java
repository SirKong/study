package com.ccnu.test.spring.simple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccnu.test.spring.simple.dao.UserDao;
import com.ccnu.test.spring.simple.model.User;

/**
 * Created by gongyb08837 on 2015/11/3.
 */
public class UserService {
    @Autowired
    private UserDao userDao;

    public void insert(User user) {
        userDao.insert(user);
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/com/ccnu/test/spring/simple/applicationContext.xml");

    }
}
