package com.yicj.study;

public class MakeStringRequest extends MethodRequest {

    private final int count ;
    private final char fillchar ;

    public MakeStringRequest(Servant servant, FutureResult<String> future, int count, char fillchar) {
        super(servant,future);
        this.count = count ;
        this.fillchar = fillchar ;
    }

    @Override
    public void execute() {
        Result<String> result = servant.makeString(count,fillchar) ;
        future.setResult(result);
    }
}
