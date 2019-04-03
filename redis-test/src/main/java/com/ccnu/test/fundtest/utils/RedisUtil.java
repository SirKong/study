package com.ccnu.test.fundtest.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

public class RedisUtil {

    private static final String REDIS_HOST = "192.168.89.128";
    private static final int REDIS_PORT = 6379;

    public static Jedis jedis;// 非切片的客户端连接
    public static JedisPool jedisPool;// 非切片连接池
    public static Pipeline pipeline;

    static {
        // 初始化池
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(5);
        config.setTestOnBorrow(false);
        jedisPool = new JedisPool(config, RedisUtil.REDIS_HOST,
                RedisUtil.REDIS_PORT);

        jedis = jedisPool.getResource();
        pipeline = jedis.pipelined();
    }

    public void closeResource() {
        jedisPool.returnResource(jedis);
    }

}
