package com.yicj.thread.lock3;

import java.util.ArrayList;
import java.util.*;
import java.util.concurrent.Delayed;
import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.*;

public class DelayQueueDemo {
    public static void main(String[] args) {
        Random random = new Random(47) ;
        ExecutorService pool = Executors.newCachedThreadPool() ;
        DelayQueue<DelayedTask> queue = new DelayQueue<>() ;
        for(int i = 0 ; i < 20 ; i ++){
            queue.put(new DelayedTask(random.nextInt(5000)));
        }
        //第21个任务
        queue.add(new EndSentinel(5000,pool)) ;
        pool.execute(new DelayedTaskConsumer(queue));
    }
    private static class DelayedTask implements Runnable, Delayed{
        private static int counter = 0 ;
        private final int id = counter ++ ;
        private final int delta ;
        private final long trigger ;
        protected static List<DelayedTask> sequence =
                new ArrayList<>() ;
        public DelayedTask(int delayInMilliseconds){
            this.delta = delayInMilliseconds ;
            trigger = System.nanoTime() +
                    NANOSECONDS.convert(delayInMilliseconds,MICROSECONDS) ;
            sequence.add(this) ;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(
      trigger - System.nanoTime(),
                    NANOSECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            DelayedTask that = (DelayedTask) o;
            if(trigger < that.trigger) return -1 ;
            if(trigger > that.trigger) return 1 ;
            return 0;
        }

        @Override
        public void run() {
            System.out.println(this + " ") ;
        }

        public String summary(){
            return "("+id+" : "+delta+")" ;
        }

        @Override
        public String toString() {
            return  "["+delta+"] Task "  +id ;
        }
    }

    private static class EndSentinel extends DelayedTask{
        private ExecutorService pool ;
        public EndSentinel(int delayInMilliseconds,ExecutorService pool) {
            super(delayInMilliseconds);
            this.pool = pool ;
        }

        @Override
        public void run() {
            System.out.println("---------queue中最后一个任务准备执行-----------");
            sequence.forEach(item -> System.out.print(item.summary()));
            System.out.println();
            System.out.println(this +" Calling shutdownNow()");
            pool.shutdownNow() ;
        }
    }

    private static class DelayedTaskConsumer implements Runnable{
        private DelayQueue<DelayedTask> q ;
        public DelayedTaskConsumer(DelayQueue<DelayedTask> q){
            this.q = q ;
        }
        @Override
        public void run() {
            try{
                while (!Thread.interrupted()){
                    q.take().run() ;//Run task with current thread
                }
            }catch (InterruptedException e){
                // Acceptable way to exit
            }
            System.out.println("Finished DelayedTaskConsumer");
        }
    }


}
