package com.yicj.thread.service;

import com.yicj.thread.annotation.Bean;
import com.yicj.thread.annotation.RequestMapping;

@Bean
public class HelloService {

    @RequestMapping("/hello")
    public Integer hello(String name, String age) {
        System.out.println("name : " + name);
        System.out.println("age : " + age);
        return  100 ;
    }
}
