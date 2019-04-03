package com.ccnu.test.springredis;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import com.ccnu.test.springredis.dao.UserDao;
import com.ccnu.test.springredis.model.User;

/**
 * Created by gongyb08837 on 2015/11/14.
 */
public class Client {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/com/ccnu/test/springredis/applicationContext.xml");

        JedisConnectionFactory connectionFactory = ac.getBean(JedisConnectionFactory.class);
        connectionFactory.getConnection().flushDb();
        System.out.println("===flush DB before operations===");

        UserDao userDao = (UserDao) ac.getBean("userDao");
        User user1 = new User(10, new Date(), 1, "User 1");
        User user2 = new User(20, new Date(), 2, "User 2");

        System.out.println("==== getting objects from redis ====");
        System.out.println("User is not in redis yet: " + userDao.get(user1));
        System.out.println("User is not in redis yet: " + userDao.get(user2));

        System.out.println("==== putting objects into redis ====");
        userDao.put(user1);
        userDao.put(user2);

        System.out.println("==== getting objects from redis ====");
        System.out.println("User should be in redis yet: " + userDao.get(user1));
        System.out.println("User should be in redis yet: " + userDao.get(user2));

        System.out.println("==== deleting objects from redis ====");
        userDao.delete(user1);
        userDao.delete(user2);

        System.out.println("==== getting objects from redis ====");
        System.out.println("User is not in redis yet: " + userDao.get(user1));
        System.out.println("User is not in redis yet: " + userDao.get(user2));


    }

}
