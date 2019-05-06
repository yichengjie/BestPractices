package com.yicj.demo.redis.stage1;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

public class SentinelTest {
    private Logger logger=LoggerFactory.getLogger(SentinelTest.class);
    private  JedisSentinelPool jSentinelPool;
    @Before
    public  void setUp(){
        String masterName="mymaster";
        //sentinel地址集合
        Set<String>set=new HashSet<String>();
        //set.add("10.221.147.32:26379");
        //set.add("10.221.147.32:26380");
        //set.add("10.221.147.32:26381");

        set.add("10.221.148.86:6382") ;
        set.add("10.221.148.87:6382") ;
        set.add("10.221.148.88:6382") ;

        GenericObjectPoolConfig gPoolConfig=new GenericObjectPoolConfig();
        gPoolConfig.setMaxIdle(10);
        gPoolConfig.setMaxTotal(10);
        gPoolConfig.setMaxWaitMillis(10);
        gPoolConfig.setJmxEnabled(true);

        jSentinelPool=new JedisSentinelPool(masterName,set,gPoolConfig);
    }
    @Test
    public void testWriet(){
        Jedis jedis=null;
        for(int i=0;i<10;i++){
            try {
                jedis=jSentinelPool.getResource();
                System.out.println(i);
                String userKey="user"+i;
                jedis.set(userKey, String.valueOf(i));
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage(),e);
            }finally{
                if (jedis!=null) {
                    jedis.close();
                }
            }
        }
    }
}
