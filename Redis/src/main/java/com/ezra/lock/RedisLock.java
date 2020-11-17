package com.ezra.lock;

import com.ezra.constant.SystemConstant;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(SystemConstant.API_URL + "/redis/lock")
public class RedisLock {

    /**
     * redisson 分布式锁实现
     */
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private RedisTemplate redisTemplate;

    private static final String PRODUCT_KEY = "PRODUCT_KEY_";
    private static final String PRODUCT = "PRODUCT_";

    /**
     * 简单模拟抢购
     */
    @GetMapping("/get")
    public void get(@RequestParam("product") String product, @RequestParam("count") Long count) {

        String LockKey = PRODUCT_KEY + product;
        RLock lock = redissonClient.getLock(LockKey);

        try {
            /**
             * waitTime   等待时间
             * leaseTime  获取到锁时最短使用时间
             */
            long l = System.currentTimeMillis();
            System.out.println("开始获取锁");
            boolean b = lock.tryLock(10l,5l, TimeUnit.SECONDS);
            if(b){
                System.out.println("获取锁成功");
            }else {
                System.out.println("获取锁失败,等待时长:" + (System.currentTimeMillis() - l));
                return;
            }
            Thread.sleep(2000);
            ValueOperations valueOperations = redisTemplate.opsForValue();
            String productKey = PRODUCT + product;
            Integer pc = (Integer) valueOperations.get(productKey);
            if (pc>0 && pc > count) {
                valueOperations.decrement(PRODUCT + product, count);
            }else {
                System.out.println("库存不足,剩余:" + pc +",抢购:" + count);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("获取锁失败");
        }finally {
            lock.unlock();
        }


    }


}
