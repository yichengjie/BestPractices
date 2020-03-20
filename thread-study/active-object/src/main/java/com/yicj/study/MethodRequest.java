package com.yicj.study;

/**
 * 主动对象MethodRequest
 */
public abstract class MethodRequest<T> {

    protected final Servant servant ;
    protected final FutureResult<T> future ;
    protected MethodRequest(Servant servant, FutureResult<T> future){
        this.servant = servant ;
        this.future = future ;
    }

    protected abstract void execute() ;
}
