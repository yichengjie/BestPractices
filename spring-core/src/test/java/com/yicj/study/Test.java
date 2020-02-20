package com.yicj.study;

import sun.reflect.Reflection;

import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) throws Exception {
        Test2 test = new Test2();
        test.g();
    }
}


class Test2 {
    public void g() throws Exception {
        gg();
    }

    public void gg() throws Exception {
        Method m = Reflection.class.getDeclaredMethod("getCallerClass",int.class);
        Object ret = m.invoke(null,1);
        System.out.println(ret);
    }

}