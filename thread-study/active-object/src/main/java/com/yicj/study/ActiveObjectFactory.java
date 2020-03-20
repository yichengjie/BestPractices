package com.yicj.study;


/**
 * Servant ： 执行实际处理的类(实现了ActiveObject接口的类)
 * ActivationQueue: 按顺序保存MethodRequest对象的类
 * SchedulerThread: 调用execute方法处理MethodRequest对象的类
 * Proxy: 将方法调用转换为MethodRequest对象的类(实现了ActiveObject接口)
 */
public class ActiveObjectFactory {

    public static ActiveObject createActiveObject(){
        Servant servant = new Servant() ;
        ActivationQueue queue = new ActivationQueue() ;
        SchedulerThread scheduler = new SchedulerThread(queue) ;
        Proxy proxy = new Proxy(scheduler,servant) ;
        scheduler.start() ;
        return proxy ;
    }
}
