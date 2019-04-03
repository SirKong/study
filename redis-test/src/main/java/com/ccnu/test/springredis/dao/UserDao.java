package com.ccnu.test.springredis.dao;

import org.springframework.data.redis.core.RedisTemplate;

import com.ccnu.test.springredis.model.User;

/**
 * Created by gongyb08837 on 2015/11/14.
 */
public interface UserDao {
    public RedisTemplate<String, User> getRedisTemplate();

    public void setRedisTemplate(RedisTemplate<String, User> redisTemplate);

    public void put(User user);

    public void delete(User key);

    public User get(User key);
}
