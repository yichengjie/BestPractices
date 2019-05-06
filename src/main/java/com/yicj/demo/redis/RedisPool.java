package com.yicj.demo.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {
	
	  private static String ADDR = "localhost";
      private static Integer PORT = 6379;
      //可用连接实例的最大数目，默认为8；
      //如果赋值为-1，则表示不限制，如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)
      private static Integer MAX_TOTAL = 1024;
      //控制一个pool最多有多少个状态为idle(空闲)的jedis实例，默认值是8
      private static Integer MAX_IDLE = 200;
      //等待可用连接的最大时间，单位是毫秒，默认值为-1，表示永不超时。
      //如果超过等待时间，则直接抛出JedisConnectionException
      private static Integer MAX_WAIT_MILLIS = 10000;
      private static Integer TIMEOUT = 10000;
      //在borrow(用)一个jedis实例时，是否提前进行validate(验证)操作；
      //如果为true，则得到的jedis实例均是可用的
      private static Boolean TEST_ON_BORROW = true;
      private static JedisPool jedisPool = null;
      static {
         try {
             JedisPoolConfig config = new JedisPoolConfig();
             config.setMaxTotal(MAX_TOTAL);
             config.setMaxIdle(MAX_IDLE);
             config.setMaxWaitMillis(MAX_WAIT_MILLIS);
             config.setTestOnBorrow(TEST_ON_BORROW);
             jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT);
      
         } catch (Exception e) {
             e.printStackTrace();
         }
      }
	  /**
	   * 获取jedis
	   *
	   * @return
	   */
	  public static Jedis getJedis() {
	      Jedis jedis = null;
	      try {
	          if (jedisPool != null)
	              jedis = jedisPool.getResource();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      return jedis;
	  }
	  /**
	   * 释放jedis资源
	   * @param jedis
	   */
	  public static void returnResource(Jedis jedis) {
	      if (jedis != null)
	          jedis.close();
	  }
}
