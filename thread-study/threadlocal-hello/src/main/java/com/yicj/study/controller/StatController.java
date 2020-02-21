package com.yicj.study.controller;

import com.yicj.study.model.Val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.*;

@RestController
public class StatController {

    //private static List<Val<Integer>> list = Collections.synchronizedList(new ArrayList<>()) ;
    private static List<Val<Integer>> list = new ArrayList<>() ;

    synchronized static void addList(Val<Integer> val){
        list.add(val) ;
    }

    private static ThreadLocal<Val<Integer>>  c = new ThreadLocal<Val<Integer>>(){
        @Override
        protected Val<Integer> initialValue() {
            Val<Integer> val = new Val<>() ;
            val.setValue(0);
            addList(val) ;//如果使用普通的ArrayList则需要使用锁
            //list.add(val) ; //可以使用线程安全的集合
            return val;
        }
    } ;

    @GetMapping("/stat")
    public Integer stat(){
        return list.stream().map(x -> x.getValue()).reduce((a,b) -> a +b).get() ;
    }

    @GetMapping("/add")
    public Integer add() throws InterruptedException {
        Thread.sleep(100);
        Val<Integer> v = c.get() ;
        v.setValue(v.getValue() + 1);
        return 1 ;
    }
}
