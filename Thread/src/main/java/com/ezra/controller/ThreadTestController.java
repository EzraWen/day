package com.ezra.controller;


import com.ezra.constant.SystemConstant;
import com.ezra.pool.ThreadPoolConfig;
import com.ezra.task.OrderTask;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping(SystemConstant.API_URL + "/thread")
public class ThreadTestController {





    @GetMapping("/test")
    public void test(@RequestParam("product") String product){
        ExecutorService threadPool = ThreadPoolConfig.THREAD_POOL;
        OrderTask orderTask = new OrderTask(product, 1l);
        Future<Boolean> future = threadPool.submit(orderTask);

        try {
            if (future.get(5l, TimeUnit.SECONDS)) {
                System.out.println("下单成功");
            }
        } catch (InterruptedException e) {
            System.out.println("future在睡着时被打断");
        } catch (ExecutionException e) {
            System.out.println("future在尝试取得任务结果时出错");
        } catch (TimeoutException e) {
            System.out.println("超时");
        }
    }
}
