package com.ezra.task;

import java.util.concurrent.Callable;

public class OrderTask implements Callable<Boolean> {

    private String product;

    private Long count;


    public OrderTask(String product, Long count) {
        this.product = product;
        this.count = count;
    }

    @Override
    public Boolean call() throws Exception {
        System.out.println("订单下单,product: " + product +  ",count:" + count);
        Thread.sleep(4000);
        //call方法需要在不想继续执行，或报错时return，否则外层超时，call方式也是会继续执行的
        return Boolean.TRUE;
    }
}
