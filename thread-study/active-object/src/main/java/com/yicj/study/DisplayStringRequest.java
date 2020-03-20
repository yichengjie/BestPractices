package com.yicj.study;

public class DisplayStringRequest extends MethodRequest {

    private final String str ;

    protected DisplayStringRequest(Servant servant, String str) {
        super(servant, null);
        this.str = str ;

    }

    @Override
    public void execute() {
        servant.displayString(str);
    }
}
