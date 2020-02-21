package com.yicj.thread.threadlocal;

import java.util.HashMap;

public class MyThreadLocal<T> {

    private T value ;

    private static HashMap<Thread,HashMap<MyThreadLocal<?>,Object>> threadLocalMap =
            new HashMap<>() ;


    private synchronized static HashMap<MyThreadLocal<?>,Object> getMap(){
        var thread = Thread.currentThread() ;
        if(!threadLocalMap.containsKey(thread)){
            threadLocalMap.put(thread,new HashMap<>()) ;
        }
        return threadLocalMap.get(thread) ;
    }

    protected T initialValue(){

        return null ;
    }

    public T get() {
       var map = getMap() ;
       if(!map.containsKey(this)){
            map.put(this,initialValue()) ;
       }
       return (T)map.get(this) ;
    }

    public void set(T value){
        var map = getMap() ;
        map.put(this,value) ;
    }
}
