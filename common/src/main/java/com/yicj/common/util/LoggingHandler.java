package com.yicj.common.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingHandler <T>implements InvocationHandler {
    private T target ;
    public T bind(T target){
        this.target = target ;
        return (T)Proxy.newProxyInstance(
                    target.getClass().getClassLoader(),
                    target.getClass().getInterfaces(),
                 this) ;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null ;
        try {
            //1记录开始日志
            this.log(String.format("%s()调用开始...",method.getName()));
            //2.设置访问权限
            //注意这里需要将method的访问权限设置为true
            //如果接口非public的话不设置访问权限为true会报错
            //类前面不写public只能同一个package下才能访问
            method.setAccessible(true);
            //3.执行原业务方法
            result = method.invoke(target,args) ;
            this.log(String.format("%s()调用结束...",method.getName()));
        } catch (IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
            log(e.toString());
        }
        return result;
    }

    public void log(String msg){
        Logger.getLogger(LoggingHandler.class.getName())
                .log(Level.INFO,msg);
    }

}