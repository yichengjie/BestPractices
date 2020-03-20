package com.yicj.future3;

import com.yicj.future.Data;
import com.yicj.future.RealData;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureData extends FutureTask<RealData> implements Data {
    public FutureData(Callable<RealData> callable) {
        super(callable);
    }

    @Override
    public String getContent() {
        String str = null ;
        try {
           str =  get().getContent() ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return str;
    }
}
