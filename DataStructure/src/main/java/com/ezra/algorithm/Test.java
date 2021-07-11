package com.ezra.algorithm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(30);

        BlockThread blockThread = new BlockThread();
        for (int i = 0; i < 5; i++) {
            int finalI = i ;
            executorService.submit(()->blockThread.tryFunction(finalI));
        }
//        AtomicInteger atomicInterger = new AtomicInteger();
//        for(int i =0; i<5;i++){
//            executorService.submit(new CustomedThread(i,atomicInterger));
//        }
    }

    static class BlockThread{
        private volatile int count = 0;

        public synchronized void tryFunction(int tag){
            int cur = 0;
            for(;cur<10;cur ++){
                count ++;
                if(0==count %2){
                    System.out.println(String.format("Thread %d slice count:%d,cur:%d",tag,count,cur));
                }

            }

        }

    }

    static class CustomedThread implements Runnable{
        private Integer blockObj;
        private int tag;
        private AtomicInteger count;

        CustomedThread(int tag,AtomicInteger count){
            this.tag = tag;
            this.count = count;
            this.blockObj = tag >>4;
        }

        @Override
        public void run() {
            synchronized (blockObj){
                int oldCount = count.get();
                int curCount = count.incrementAndGet();
                System.out.println(String.format("CustomedThread tag is %d,oldCount is %d, curCount is %d",tag,oldCount,curCount));
            }
        }
    }



}
