package com.yicj.study;

/**
 * 主动对象:SchedulerThread
 * SchedulerThread类是Active Object模式的核心部分
 */
public class SchedulerThread extends Thread {
    private final ActivationQueue queue ;

    public SchedulerThread(ActivationQueue queue) {
        this.queue = queue ;
    }


    public void invoke(MethodRequest request) {
        queue.putRequest(request) ;
    }

    @Override
    public void run() {
        while (true){
            MethodRequest request = queue.takeRequest() ;
            request.execute() ;
        }
    }
}
