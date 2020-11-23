package com.ezra.pool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@Component
public class ThreadPoolConfig {


    @Bean
    public ExecutorService executorService(){
        return newFixedThreadPool(10);
    }

    /**
     * 限定等待队列中的任务数量,避免任务过多而OOM
     * 修改keepAlive的时间,使非核心的线程超过时间没有任务执行后,被回收
     * 使用guava提供的线程工厂创造,对线程名称格式化,或者设置为守护线程
     * 使用默认的任务拒绝策略
     * @param nThreads
     * @return
     */
    private static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                1000L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(10000),new ThreadFactoryBuilder().setNameFormat("Thread-pool-%d").build());
    }


}
