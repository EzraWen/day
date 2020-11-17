package com.ezra.hash;

import cn.hutool.core.bean.BeanUtil;
import com.ezra.constant.SystemConstant;
import com.ezra.dto.RedisValueDTO;
import com.ezra.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(SystemConstant.API_URL + "/redis/hash")
public class RedisHash {

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/set")
    public Result set(@RequestBody Map map, @RequestParam("key") String key) {
        redisTemplate.opsForHash().putAll(key,  map);
        return Result.SUCCESS;
    }

    @PostMapping("/setOne")
    public Result setOne(@RequestParam("key") String key,@RequestParam("hashkey") String hashkey,@RequestParam("val") String val){
        redisTemplate.opsForHash().put(key,hashkey,val);
        return Result.SUCCESS;
    }

    @PostMapping("/get")
    public Result get(@RequestParam("key") String key,@RequestParam("hashkey") String hashkey){
        return Result.data(redisTemplate.opsForHash().get(key,hashkey));
    }






}