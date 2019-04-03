package com.ccnu.test.redis.channels;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Program {

    public static final String CHANNEL_NAME = "commonChannel";

    private static Logger logger = LoggerFactory.getLogger(Program.class);

    public static void main(String[] args) throws Exception {

        JedisPoolConfig poolConfig = new JedisPoolConfig();

        JedisPool jedisPool = new JedisPool(poolConfig, "192.168.89.128", 6379,
                0);

        final Jedis subscriberJedis = jedisPool.getResource();

        final Subscriber subscriber = new Subscriber();
        new Thread(new Runnable() {
            public void run() {
                try {
                    logger.info("订阅 \"commonChannel\"上面的消息. 这个线程会被阻塞，直到有发布消息推送过来.");
                    subscriberJedis.subscribe(subscriber, CHANNEL_NAME);
                    logger.info("订阅结束.");
                } catch (Exception e) {
                    logger.error("订阅失败.", e);
                }
            }
        }).start();
        Jedis publisherJedis = jedisPool.getResource();

        new Publisher(publisherJedis, CHANNEL_NAME).start();

        subscriber.unsubscribe();
        jedisPool.returnResource(subscriberJedis);
        jedisPool.returnResource(publisherJedis);
    }
}