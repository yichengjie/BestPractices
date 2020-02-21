package com.yicj.thread.semaphore.pool;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Pool<T> {
    //对象个数
    private List<T> items = new ArrayList<>();
    private boolean [] checkedOut  ;
    private Semaphore avaliable ;
    public Pool(Class<T> clazz, Integer size){
        checkedOut = new boolean[size] ;
        avaliable = new Semaphore(size,true) ;
        try{
            for(int i = 0 ; i < size ; i ++ ){
                items.add(clazz.newInstance()) ;
                checkedOut[i] = false;
            }
        }catch (Exception e){
            throw new RuntimeException(e) ;
        }
    }

    public T checkout() {
        try {
            avaliable.acquire();
            return this.getItem() ;
        }catch (InterruptedException e){
            throw new RuntimeException(e) ;
        }
    }

    public void checkin(T obj){
       if(this.releaseItem(obj)){
            avaliable.release();
       }
    }

    private synchronized boolean releaseItem(T obj){
        int index = items.indexOf(obj) ;
        if(index == -1){
            return false ;
        }
        if(checkedOut[index]){
            checkedOut[index] = false ;
            return true ;
        }
        return false ;
    }

    private synchronized T getItem(){
       for(int i = 0 ; i < checkedOut.length ;i ++){
            if(!checkedOut[i]){
                checkedOut[i] = true ;
                return items.get(i) ;
            }
       }
       return null ;
    }

}
