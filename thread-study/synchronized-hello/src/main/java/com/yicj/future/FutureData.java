package com.yicj.future;

public class FutureData implements Data{

    private RealData realData ;
    private boolean ready ;


    public synchronized void setRealData(RealData realData){
        if (ready){
            return; //balk
        }
        this.realData = realData ;
        this.ready = true ;
        notifyAll();
    }



    @Override
    public synchronized String getContent() {
        while (!ready){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.getContent();
    }
}
