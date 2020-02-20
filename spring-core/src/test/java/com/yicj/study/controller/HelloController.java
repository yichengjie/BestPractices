package com.yicj.study.controller;


import com.yicj.study.annotation.AutoWired;
import com.yicj.study.annotation.Controller;
import com.yicj.study.annotation.RequestMapping;
import com.yicj.study.annotation.RequestParam;
import com.yicj.study.service.HelloService;

@Controller
public class HelloController {

    @AutoWired
    private HelloService helloService ;

    public Integer hello(@RequestParam("name") String name, @RequestParam("age") String age){

        return this.helloService.hello(name,age) ;
    }

}
