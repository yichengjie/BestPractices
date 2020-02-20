package com.yicj.study.service;

import com.yicj.study.annotation.Bean;
import com.yicj.study.annotation.RequestMapping;

@Bean
public class HelloService {

    @RequestMapping("/hello")
    public Integer hello(String name, String age) {
        System.out.println("name : " + name);
        System.out.println("age : " + age);
        return  100 ;
    }
}
