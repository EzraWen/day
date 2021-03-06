package com.ezra.controller;


import com.ezra.constant.SystemConstant;
import com.ezra.service.MomentCorpLimitService;
import com.ezra.task.OrderTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@RestController
@RequestMapping(SystemConstant.API_URL + "/thread")
public class ThreadTestController {


    @Autowired
    private ExecutorService executorService;

    @Autowired
    private MomentCorpLimitService momentCorpLimitService;

    @GetMapping("/test")
    public void test(@RequestParam("product") String product) {

        OrderTask orderTask = new OrderTask(product, 1l);
        Future<Boolean> future = executorService.submit(orderTask);

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


    @GetMapping("/test2")
    public void test2() {
        momentCorpLimitService.countHandler("123");
//        List<String> data = new ArrayList<>();
//        data.add("123");
//        data.add("124");
//        data.add("123");
//        data.add("123");
//        data.add("125");
//        data.add("126");
//        data.add("126");
//
//        ArrayList<Future> futures = new ArrayList<>();
//        data.forEach(it->{
//            Future<Boolean> future = executorService.submit(() ->
//
//            );
//
//            futures.add(future);
//        });
//
//
//        for (Future future : futures) {
//            try {
//                future.get();
//            } catch (InterruptedException e) {
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        }


    }





    /**
     * 测试多线程执行取消任务
     */

    public void test1(){
        int taskNumber = 5;
        CountDownLatch countDownLatch = new CountDownLatch(5);
        ArrayList<Future> futures = new ArrayList<>();
        for (int i = 0; i < taskNumber; i++) {


            FutureTask<Boolean> booleanFutureTask = new FutureTask<Boolean>(()->{
                //实际执行的业务方法
                return true;
            });
            futures.add(booleanFutureTask);
            executorService.execute(booleanFutureTask);
        }


        //外层countdownlatch   带入子线程
        try {
            countDownLatch.await(20l, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            //超时了
        }
    }

}
