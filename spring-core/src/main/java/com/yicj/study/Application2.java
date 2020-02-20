package com.yicj.study;

import com.yicj.study.bean.BeanFactory;
import com.yicj.study.handler.HandlerManager;
import com.yicj.study.scanner.ClassScanner2;

import java.util.List;
import java.util.Set;


public class Application2 {

    public static void main(String[] args) throws Exception {
        List<Class<?>> classes = ClassScanner2.getClasses("com.yicj.study");
        BeanFactory.initBean(classes);
        HandlerManager.resolveMappingHandler(classes); ;
        classes.forEach(clazz -> System.out.println(clazz));
    }
}
