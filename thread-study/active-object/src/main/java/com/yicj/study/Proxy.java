package com.yicj.study;

/**
 * 主动对象方：Proxy类
 * Proxy类会被MakerClientThread类和DisplayClientThread类调用，
 * 其任务在于将方法的调用转换为对象(实例)
 *
 */
public class Proxy implements ActiveObject{
    private final SchedulerThread scheduler ;
    private final Servant servant ;
    public Proxy(SchedulerThread scheduler, Servant servant) {
        this.scheduler = scheduler ;
        this.servant = servant ;
    }

    @Override
    public Result<String> makeString(int count, char fillchar) {
        FutureResult<String> future = new FutureResult<String>() ;
        scheduler.invoke(new MakeStringRequest(servant, future, count, fillchar)) ;
        return future;
    }

    @Override
    public void displayString(String str) {
        scheduler.invoke(new DisplayStringRequest(servant,str));
    }
}
