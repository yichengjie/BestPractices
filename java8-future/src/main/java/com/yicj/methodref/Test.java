package com.yicj.methodref;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        //静态方法引用
        Arrays.asList("a", "c", "b").stream().forEach(Test::println2);
        Test test = new Test() ;
        // lambda表达式使用：
        Arrays.asList("a", "c", "b").stream().forEach(s -> test.println(s));
        //特定对象的实例方法引用
        Arrays.asList("a", "c", "b").stream().forEach(test::println);
    }

    public void println(String str){

        System.out.println(str);
    }

    public static void println2(String str){

        System.out.println(str);
    }
}
