package com.yicj.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class MethodName {
    public String hello(String hehe,String haha){
        return "world";
    }

    public static void main(String[] args) {
        Class<MethodName> clz = MethodName.class;
        Method[] methods = clz.getMethods();
        for(int i=0;i<methods.length;i++){
            Method method = methods[i];
            if(method.getName().equals("hello")){
                Parameter[] parameters = method.getParameters();
                //通过debug看到的名称是arg0和arg1。
            }
        }
    }
}
