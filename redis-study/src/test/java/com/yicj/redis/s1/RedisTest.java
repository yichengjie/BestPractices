package com.yicj.redis.s1;

import redis.clients.jedis.Jedis;

public class RedisTest {
	public static void main(String[] args) {
        Jedis jedis =
                RedisPool.getJedis();
        jedis.select(2);
        System.out.println("");
    }
}
