package com.ezra.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ThreadPoolConfig {

    public static final ExecutorService THREAD_POOL = newFixedThreadPool(10);


    private static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                1000L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    //需要维护线程池的生命周期
}
