package com.yicj.future2;

import com.yicj.future.Data;
import com.yicj.future.RealData;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Host {

    public FutureTask<Data> request(final int count, final char c){
        System.out.println("   request ("+count+", " + c +") BEGIN");
        //1. 创建Future类型的实例
        FutureTask<Data> future = new FutureTask<Data>(new Callable<Data>() {
            @Override
            public Data call() throws Exception {
                return new RealData(count, c);
            }
        }) ;
        //2. 启动一个新线程，用于创建RealData的实例
        new Thread(future).start();
        System.out.println("  request ("+count +", "+ c +") END");

        //3. 返回future的实例
        return future ;
    }
}
