package com.ezra.lock;

import com.ezra.constant.SystemConstant;
import org.redisson.api.RLock;
import org.redisson.api.RPermitExpirableSemaphore;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
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
     * 以下是最简单的实现使用方式,由于获取锁时设置了leaseTime,申请了锁的时间,超时是自动释放的,在实际生产使用还需要在调整
     */
    @GetMapping("/get")
    public void get(@RequestParam("product") String product, @RequestParam("count") Long count) {

        String LockKey = PRODUCT_KEY + product;
        RLock lock = redissonClient.getLock(LockKey);
        boolean b = false;
        try {

            long l = System.currentTimeMillis();
            System.out.println("开始获取锁");
            /**
             * waitTime   等待时间
             * leaseTime  获取到锁时最短使用时间
             */
            b = lock.tryLock(10l, 5l, TimeUnit.SECONDS);
            if (b) {
                System.out.println("获取锁成功");
            } else {
                System.out.println("获取锁失败,等待时长:" + (System.currentTimeMillis() - l));
                return;
            }
            Thread.sleep(10);
            ValueOperations valueOperations = redisTemplate.opsForValue();
            String productKey = PRODUCT + product;
            Integer pc = (Integer) valueOperations.get(productKey);
            if (pc > 0 && pc >= count) {
                System.out.println("抢购成功,库存-" + count);
                valueOperations.decrement(PRODUCT + product, count);
            } else {
                System.out.println("商品抢购结束,库存不足,剩余:" + pc + ",抢购:" + count);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("获取锁失败,给用户提示抢购失败");
        } finally {
            if (b) {
                lock.unlock();
            }
        }
    }


    /**
     * 以下是测试获取锁后服务超时的情况,第一种情况,全局声明看门狗监控超时的,这种是锁续期的
     */
    @GetMapping("/getOvertimeLock")
    public void getOvertimeLock(@RequestParam("product") String product) {
        String LockKey = PRODUCT_KEY + product;
        RLock lock = redissonClient.getLock(LockKey);
        boolean b = false;
        try {
            b = lock.tryLock(10, TimeUnit.SECONDS);
            //获取之后查看锁剩余时间,暂停100s
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if (b) {
                lock.unlock();
            }
        }

    }


    /**
     * 信号量 限流
     */
    @GetMapping("/semaphore")
    public void semaphore(@RequestParam("product") String product){
        String LockKey = PRODUCT_KEY + product;
        RPermitExpirableSemaphore semaphore = redissonClient.getPermitExpirableSemaphore(LockKey);
        semaphore.trySetPermits(5);
        String id = null;
        try {
            id = semaphore.acquire(2, TimeUnit.SECONDS);
            //执行业务
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            System.out.println("获取信号量失败");
            return;
        }
        boolean b = semaphore.tryRelease(id);

        if (b) {
            System.out.println("成功");
        }else {
            //过期
            System.out.println("过期失败");
        }

    }




}
