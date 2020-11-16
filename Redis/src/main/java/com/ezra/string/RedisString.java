package com.ezra.string;

import com.ezra.constant.SystemConstant;
import com.ezra.dto.RedisObject;
import com.ezra.dto.RedisValueDTO;
import com.ezra.response.MsgCode;
import com.ezra.response.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(SystemConstant.API_URL + "/redis/string")
public class RedisString {

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/set")
    public Result set(@RequestParam("key") String key, @RequestParam("val") String val) {
        redisTemplate.opsForValue().set(key, val);
        return Result.SUCCESS;
    }

    @PostMapping("/get")
    public Result get(@RequestParam("key") String key) {
        Object o = redisTemplate.opsForValue().get(key);
        return new Result(MsgCode.CODE_SUCCESS, MsgCode.MSG_SUCCESS, o);
    }

    @PostMapping("/setExpire")
    public Result setExpire(@RequestParam("key") String key, @RequestParam("val") String val, @RequestParam("expire") Long expire) {
        redisTemplate.opsForValue().set(key, val, expire, TimeUnit.SECONDS);
        return Result.SUCCESS;
    }

    @PostMapping("/remove")
    public Result remove(@RequestParam("key") String key) {
        redisTemplate.delete(key);
        return Result.SUCCESS;
    }


    @PostMapping("/setObject")
    public Result setObject(@RequestBody RedisValueDTO dto, @RequestParam("key") String key) {
        redisTemplate.opsForValue().set(key, dto);
        return Result.SUCCESS;
    }

    @PostMapping("/getObject")
    public Result getObject(@RequestParam("key") String key) throws IOException {
        Object val = redisTemplate.opsForValue().get(key);
        RedisValueDTO redisValueDTO = (RedisValueDTO) val;
        return Result.data(redisValueDTO);
    }


}
