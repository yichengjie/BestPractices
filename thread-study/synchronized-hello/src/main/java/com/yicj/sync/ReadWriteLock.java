package com.yicj.sync;

public final class ReadWriteLock {

    private int readingReaders = 0 ;//实际正在读取中的线程个数
    private int waitingWriters = 0 ;//正在等待写入的线程个数
    private int writingWriters = 0 ;//实际正在写入中的线程个数
    private boolean preferWriter = true ;//若写入优先，则为true

    public synchronized void readLock() throws InterruptedException {
        while (writingWriters > 0 || (preferWriter && waitingWriters > 0)){
            this.wait();
        }
        readingReaders ++ ;
    }

    public synchronized void readUnlock(){
        readingReaders -- ;
        preferWriter = true ;
        notifyAll();
    }


    public synchronized void writeLock() throws InterruptedException {
        waitingWriters ++ ;//正在等待写入的线程数加1
        try {
            while (readingReaders > 0 || writingWriters > 0){
                this.wait();
            }
        }finally {
            waitingWriters -- ;  //正在等待写入的线程个数加1
        }
        writingWriters ++ ;
    }

    public synchronized void writeUnlock(){
        writingWriters -- ;
        preferWriter = false ;
        notifyAll();
    }

}
