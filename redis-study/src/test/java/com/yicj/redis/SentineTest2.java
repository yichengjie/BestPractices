package com.yicj.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zhoum on 2018-06-22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-sentine.xml"})
public class SentineTest2 {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void test1(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("first","hellow word");
        System.out.println("=====> : " + valueOperations.get("first"));
    }
}
