package com.yicj.study;

import java.io.IOException;
import java.util.List;

public class Application {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Class<?>> classList = ClassScanner.scanClasses(Application.class.getPackage().getName());
        System.out.println(classList);
    }
}
