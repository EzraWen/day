package com.ezra.set;


import com.ezra.constant.SystemConstant;
import com.ezra.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(SystemConstant.API_URL + "/redis/set")
public class RedisSet {

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/set")
    public Result set(@RequestBody Map map, @RequestParam("key") String key){
        redisTemplate.opsForSet().add(key,map);
        return Result.SUCCESS;
    }

    @PostMapping("/setString")
    public Result setString(@RequestParam("key") String key,@RequestParam("str") String str){
        redisTemplate.opsForSet().add(key,str);
        return Result.SUCCESS;
    }

    /**
     * key1和key2的集合元素不同的 ,只返回key1中的数据,不会改变原数据,仅返回结果
     * @param key1
     * @param key2
     * @return
     */
    @PostMapping("/diff")
    public Result diff(@RequestParam("key1") String key1,@RequestParam("key2") String key2){
        Set difference = redisTemplate.opsForSet().difference(key1, key2);
        return Result.data(difference);
    }

    /**
     * key1和key2的集合元素相同的 ,只返回key1中的数据,不会改变原数据,仅返回结果
     * @param key1
     * @param key2
     * @return
     */
    @PostMapping("/inter")
    public Result inter(@RequestParam("key1") String key1,@RequestParam("key2") String key2){
        Set difference = redisTemplate.opsForSet().intersect(key1, key2);
        return Result.data(difference);
    }


    /**
     * key1和key2的集合合并后结果,不改变元数据,仅返回结果
     * @param key1
     * @param key2
     * @return
     */
    @PostMapping("/union")
    public Result union(@RequestParam("key1") String key1,@RequestParam("key2") String key2){
        Set difference = redisTemplate.opsForSet().union(key1, key2);
        return Result.data(difference);
    }

}
