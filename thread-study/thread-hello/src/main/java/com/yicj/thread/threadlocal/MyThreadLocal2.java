package com.yicj.thread.threadlocal;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadLocal2 <T>{

    private T value ;
    private static AtomicInteger atomic = new AtomicInteger() ;
    private Integer threadLocalHash = atomic.addAndGet(0x61c88647);


    private static HashMap<Thread, HashMap<Integer,Object>> threadLocalMap =
            new HashMap<>() ;


    private synchronized static HashMap<Integer,Object> getMap(){
        Thread thread = Thread.currentThread() ;
        if(!threadLocalMap.containsKey(thread)){
            threadLocalMap.put(thread,new HashMap<>()) ;
        }
        return threadLocalMap.get(thread) ;
    }

    protected T initialValue(){

        return null ;
    }

    public T get() {
        HashMap<Integer,Object> map = getMap() ;
        if(!map.containsKey(this.threadLocalHash)){
            map.put(this.threadLocalHash,initialValue()) ;
        }
        return (T)map.get(this.threadLocalHash) ;
    }

    public void set(T value){
        HashMap<Integer,Object> map = getMap() ;
        map.put(this.threadLocalHash,value) ;
    }
}
