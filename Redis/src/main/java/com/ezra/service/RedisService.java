package com.ezra.service;

import com.ezra.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;


    @Transactional
    public Result testTransaction(){

        ValueOperations valueOperations = redisTemplate.opsForValue();

        valueOperations.set("tt1" ,1);
        valueOperations.set("tt2" ,2);
        valueOperations.increment("tt1");
        int e  = 6/0;
        return Result.SUCCESS;
    }
}
