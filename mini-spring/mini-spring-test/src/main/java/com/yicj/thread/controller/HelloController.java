package com.yicj.thread.controller;


import com.yicj.thread.annotation.AutoWired;
import com.yicj.thread.annotation.Controller;
import com.yicj.thread.annotation.RequestMapping;
import com.yicj.thread.annotation.RequestParam;
import com.yicj.thread.service.HelloService;

@Controller
public class HelloController {

    @AutoWired
    private HelloService helloService ;

    @RequestMapping("/hello")
    public Integer hello(@RequestParam("name") String name, @RequestParam("age") String age){

        return this.helloService.hello(name,age) ;
    }

}
