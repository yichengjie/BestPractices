package com.yicj.thread.lock3;

import com.yicj.common.generator.Generator;
import com.yicj.common.util.BasicGenerator;
import com.yicj.thread.semaphore.pool.Fat;

import java.util.List;
import java.util.concurrent.*;

public class ExchangerDemo {
    static int size = 10 ;
    static int delay = 5 ;//Seconds

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();
        Exchanger<List<Fat>> xc = new Exchanger<>() ;
        List<Fat> producerList = new CopyOnWriteArrayList<>() ;
        List<Fat> consumerList = new CopyOnWriteArrayList<>() ;
        pool.execute(new ExchangeProducer<>(
            xc, BasicGenerator.create(Fat.class),producerList));
        pool.execute(new ExchangerConsumer<Fat>(xc,consumerList));
        TimeUnit.SECONDS.sleep(delay);
        pool.shutdownNow() ;
    }

    private static class ExchangeProducer<T> implements Runnable{
        private Generator<T> generator ;
        private Exchanger<List<T>> exchanger ;
        private List<T> holder ;
        public ExchangeProducer(Exchanger<List<T>> exchg,
                    Generator<T> gen ,List<T> holder){
            this.exchanger = exchg ;
            this.generator = gen ;
            this.holder = holder ;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    for (int i = 0 ; i < ExchangerDemo.size ; i++){
                        holder.add(generator.next()) ;
                    }
                    //Exchange full for empty
                    holder = exchanger.exchange(holder) ;
                }
            }catch (InterruptedException e){
                // ok to terminate this way
            }
        }
    }

    private static class ExchangerConsumer<T> implements Runnable{
        private Exchanger<List<T>> exchanger ;
        private List<T> holder ;
        private volatile T value ;

        public ExchangerConsumer(Exchanger<List<T>> ex,
                         List<T> holder){
            this.exchanger = ex ;
            this.holder = holder ;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    holder = exchanger.exchange(holder) ;
                    for(T x : holder){
                        value = x ;//Fetch out value
                        holder.remove(x) ;// ok for CopyOnWriteArrayList
                    }
                }
            }catch (InterruptedException e){
                // ok to terminate this way
            }
            System.out.println("Final value : " + value);
        }
    }

}
