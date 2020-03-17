package com.yicj.thread.hello;

public class Val<T> {
    T value ;
    public T getValue(){
        return value ;
    }

    public void setValue(T value){
        this.value = value ;
    }
}
