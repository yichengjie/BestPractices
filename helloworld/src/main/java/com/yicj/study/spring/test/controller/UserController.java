package com.yicj.study.spring.test.controller;

import com.yicj.study.spring.core.annotation.Controller;
import com.yicj.study.spring.core.annotation.RequestMapping;
import com.yicj.study.spring.core.annotation.RequestParam;

@Controller
public class UserController {

    @RequestMapping("/user/saveUser")
    public void addUser(@RequestParam("name") String name ,String addr ,@RequestParam("email") Integer email){
        System.out.println("hello world" + name);
    }

    @RequestMapping("/user/deleteUser")
    public void deleteUser(@RequestParam("id") String id){
        System.out.println("hello world" + id);
    }

    public void updateUser(@RequestParam("id") String id){
        System.out.println("hello world" + id);
    }
}
