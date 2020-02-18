package com.yicj.study;

import java.util.Set;

public class Application2 {

    public static void main(String[] args) throws Exception {
        Set<Class<?>> classes = ClassScanner2.getClasses(Application2.class.getPackage().getName());
        System.out.println(classes);
    }
}
