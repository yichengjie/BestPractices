package com.yicj.thread.lock3;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

//这个是一个很基础的优先级队列,它具有可阻塞的读取操作
public class PriorityBlockingQueueDemo {

    public static void main(String[] args) {
        Random rand = new Random(47) ;
        ExecutorService pool = Executors.newCachedThreadPool();
        PriorityBlockingQueue<Runnable> queue =
                new PriorityBlockingQueue<>() ;
        pool.execute(new PrioritizedTaskProducer(queue,pool));
        pool.execute(new PrioritizedTaskConsumer(queue));

    }


    private static class PrioritizedTask implements
            Runnable ,Comparable<PrioritizedTask>{
        private Random random = new Random(47) ;
        private static int counter = 0 ;
        private final int id = counter ++ ;
        private final int priority ;
        protected static List<PrioritizedTask> sequence =
                new ArrayList<>() ;
        public PrioritizedTask(int priority){
            this.priority = priority ;
            sequence.add(this) ;
        }

        @Override
        public int compareTo(PrioritizedTask that) {
            //小于   等于    大于
            // -1    0       1
            //上面的方式会以正序方式排序
            //如果反过来则以倒序排列，所以这里需要反过来
            if(this.priority < that.priority) return 1 ;
            if(this.priority > that.priority) return -1 ;
            return 0;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(random.nextInt(250));
            }catch (InterruptedException e){
                // Acceptable way to exit
            }
            System.out.println("PrioritizedTask 执行 : " + this);
        }

        @Override
        public String toString() {
            return "priority: ["+priority+"] Task id [" + id+"]" ;
        }
        public String summary(){
            return "( id: "+id+") : priority("+priority+")" ;
        }
    }

    private static class EndSentinel extends PrioritizedTask{
        private ExecutorService pool ;
        public EndSentinel(ExecutorService pool) {
            super(-1); //Lowest priority in this program
            this.pool = pool ;
        }

        @Override
        public void run() {
            int count = 0 ;
            System.out.println("------------最有一个task打印信息--------------");
            for (PrioritizedTask pt : sequence){
                System.out.print(pt.summary() +" ");
                if(++count % 5 == 0){
                    System.out.println();
                }
            }
            System.out.println();
            System.out.println(this + " Calling shutdownNoew()");
            pool.shutdownNow() ;
        }
    }

    private static class PrioritizedTaskProducer
            implements Runnable{
        private Random rand = new Random(47) ;
        private Queue<Runnable> queue  ;
        private ExecutorService pool ;

        public PrioritizedTaskProducer(Queue<Runnable> q , ExecutorService e){
            this.queue = q ;
            this.pool = e  ; // Used for endSentinel
        }

        @Override
        public void run() {
            //fill it up fast with rand priorities
            for(int i = 0 ; i < 20 ; i ++){
                queue.add(new PrioritizedTask(rand.nextInt(10))) ;
                Thread.yield();
            }
            try {
                // Trickle in highest-priority jobs
                for(int i = 0 ; i < 10 ; i++){
                    TimeUnit.MILLISECONDS.sleep(250);
                    queue.add(new PrioritizedTask(10)) ;
                }
                // Add jobs , lowest priority fist
                for (int i = 0 ; i < 10 ; i++){
                    queue.add(new PrioritizedTask(i)) ;
                }
                // A sentinel to stop all the tasks:
                queue.add(new EndSentinel(pool)) ;
            }catch (InterruptedException e){
                // Acceptable Way to exit
            }
            System.out.println("生产 PrioritizedTaskProducer 执行完成");
        }
    }

    private static class PrioritizedTaskConsumer
            implements Runnable{
        private PriorityBlockingQueue<Runnable> q ;
        public PrioritizedTaskConsumer(PriorityBlockingQueue<Runnable> q){
            this.q = q  ;
        }
        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    //Use current thread to run the task
                    q.take().run();
                }
            }catch (InterruptedException e){
                // Acceptable way to exit
            }
            System.out.println("消费 PrioritizedTaskConsumer 执行完成");
        }
    }

}
