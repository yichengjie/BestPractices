package com.yicj.study;

import com.yicj.study.scanner.ClassScanner2;

import java.util.Set;

public class Application2 {

    public static void main(String[] args) throws Exception {
        Set<Class<?>> classes = ClassScanner2.getClasses("com.yicj");
        System.out.println(classes);
    }
}
