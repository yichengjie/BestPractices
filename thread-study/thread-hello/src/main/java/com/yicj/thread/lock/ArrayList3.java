package com.yicj.thread.lock;

import java.util.Arrays;
import java.util.concurrent.locks.StampedLock;

public class ArrayList3<E> {

    private int next ;
    private Object [] elems ;
    private StampedLock lock = new StampedLock() ;


    public ArrayList3(){
        this(16) ;
    }

    public ArrayList3(int capacity){
        elems = new Object[capacity] ;
    }


    public void add(E elem){
        long stamp = lock.writeLock();
        try {
            if(next == elems.length){
                elems = Arrays.copyOf(elems,elems.length * 2) ;
            }
            elems[next++] = elem ;
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    public E get(int index){

        long stamp = lock.tryOptimisticRead() ;
        Object elem = elems[index] ;
        if(!lock.validate(stamp)){
            stamp = lock.readLock();
            try {
                elem = elems[index] ;
            }finally {
                lock.unlockRead(stamp);
            }
        }
        return (E)elem ;

    }

    public int size(){
       long stamp =  lock.tryOptimisticRead() ;
       int size = next ;
       if(!lock.validate(stamp)){
           stamp = lock.readLock() ;
           try{
               size = next ;
           }finally {
               lock.unlockRead(stamp);
           }
       }
       return size ;
    }

}
