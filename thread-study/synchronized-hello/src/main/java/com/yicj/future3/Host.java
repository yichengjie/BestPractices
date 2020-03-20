package com.yicj.future3;

import com.yicj.future.RealData;
import java.util.concurrent.Callable;

public class Host {

    public FutureData request(final int count, final char c){
        System.out.println("   request ("+count+", " + c +") BEGIN");
        //1. 创建Future类型的实例
        FutureData future = new FutureData(new Callable<RealData>() {
            @Override
            public RealData call() throws Exception {
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
