package com.yicj.demo.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {
	
	  private static String ADDR = "localhost";
      private static Integer PORT = 6379;
      //��������ʵ���������Ŀ��Ĭ��Ϊ8��
      //�����ֵΪ-1�����ʾ�����ƣ����pool�Ѿ�������maxActive��jedisʵ�������ʱpool��״̬Ϊexhausted(�ľ�)
      private static Integer MAX_TOTAL = 1024;
      //����һ��pool����ж��ٸ�״̬Ϊidle(����)��jedisʵ����Ĭ��ֵ��8
      private static Integer MAX_IDLE = 200;
      //�ȴ��������ӵ����ʱ�䣬��λ�Ǻ��룬Ĭ��ֵΪ-1����ʾ������ʱ��
      //��������ȴ�ʱ�䣬��ֱ���׳�JedisConnectionException
      private static Integer MAX_WAIT_MILLIS = 10000;
      private static Integer TIMEOUT = 10000;
      //��borrow(��)һ��jedisʵ��ʱ���Ƿ���ǰ����validate(��֤)������
      //���Ϊtrue����õ���jedisʵ�����ǿ��õ�
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
	   * ��ȡjedis
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
	   * �ͷ�jedis��Դ
	   * @param jedis
	   */
	  public static void returnResource(Jedis jedis) {
	      if (jedis != null)
	          jedis.close();
	  }
}
