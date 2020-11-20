package com.ezra.pool;

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

    private static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                1000L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());
    }


}
