package com.ezra.task;

import java.util.concurrent.Callable;

public class Order1Task implements Callable<Boolean> {


    public Order1Task() {

    }

    @Override
    public Boolean call() throws Exception {
        Thread.sleep(10000);
        //call方法需要在不想继续执行，或报错时return，否则外层超时，call方式也是会继续执行的
        return Boolean.TRUE;
    }
}
