package com.yicj.duotai.jaxb;


import org.junit.Test;

import java.io.File;

public class App1 {
    @Test
    public void saveXmlTest() {
        User user = new User("陈本布衣", 2018, "超级管理员","瞎哔哔");
        user.setUserName("");
        File file = new File("E://user.xml");
        JaxbUtil.convertToXml(user, file);
    }

    @Test
    public void getUserTest() {
    	File file = new File("E://user.xml");
    	User user = JaxbUtil.convertToJavaBean(User.class, file);
    	System.out.println(user);
    	
    }
}