package com.ccnu.test.springredis.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.ccnu.test.springredis.dao.UserDao;
import com.ccnu.test.springredis.model.User;

/**
 * Created by gongyb08837 on 2015/11/14.
 */
@Service("userDao")
public class UserDaoImpl implements UserDao {
    @Autowired
    RedisTemplate<String, User> redisTemplate;

    public RedisTemplate<String, User> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, User> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void put(User user) {
        redisTemplate.opsForHash().put(User.getObjectKey(), user.getId(), user);
    }

    public void delete(User key) {
        redisTemplate.opsForHash().delete(User.getObjectKey(), key.getId());
    }

    public User get(User key) {
        return (User) redisTemplate.opsForHash().get(User.getObjectKey(), key.getId());
    }

}
