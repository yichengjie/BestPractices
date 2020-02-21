package com.yicj.study.model;

public class Val<T> {
    T value ;
    public T getValue(){
        return value ;
    }

    public void setValue(T value){
        this.value = value ;
    }
}
