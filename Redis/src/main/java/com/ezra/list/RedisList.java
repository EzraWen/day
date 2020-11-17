package com.ezra.list;


import com.ezra.constant.SystemConstant;
import com.ezra.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SystemConstant.API_URL + "/redis/list")
public class RedisList {


    @Autowired
    private RedisTemplate redisTemplate;

    /**
     *                 i    i   i   i   i
     *  key       left[val,val,val,val,val] right
     *  push 推入   pop 弹出
     *
     *  可实现队列(先进先出,左进右出),栈(后进先出,左进左出)
     */


    /**
     * 左进
     * @param key
     * @param val
     * @return
     */
    @PostMapping("/set")
    public Result set(@RequestParam("key") String key,@RequestParam("val") Object val){
        redisTemplate.opsForList().leftPush(key,val);
        return Result.SUCCESS;
    }


    @PostMapping("/leftPop")
    public Result leftPop(@RequestParam("key") String key){
        return Result.data(redisTemplate.opsForList().leftPop(key));
    }

    @PostMapping("/rightPop")
    public Result rightPop(@RequestParam("key") String key){
        return Result.data(redisTemplate.opsForList().rightPop(key));
    }
}
