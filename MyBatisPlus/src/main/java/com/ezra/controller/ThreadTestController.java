package com.ezra.controller;


import com.ezra.constant.SystemConstant;

import com.ezra.service.MomentCorpLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@RestController
@RequestMapping(SystemConstant.API_URL + "/thread")
public class ThreadTestController {


    @Autowired
    private ExecutorService executorService;

    @Autowired
    private MomentCorpLimitService momentCorpLimitService;


    @GetMapping("/test2")
    public void test2() {

        List<String> data = new ArrayList<>();
        data.add("123");
        data.add("124");
        data.add("123");
        data.add("123");
        data.add("125");
        data.add("126");
        data.add("126");

        ArrayList<Future> futures = new ArrayList<>();
        data.forEach(it->{
            Future<Boolean> future = executorService.submit(() -> momentCorpLimitService.countHandler(it)
            );

            futures.add(future);
        });


        for (Future future : futures) {
            try {
                future.get();
            } catch (InterruptedException e) {
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }


    }


}
