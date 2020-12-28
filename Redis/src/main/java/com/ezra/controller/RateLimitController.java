package com.ezra.controller;

import com.ezra.constant.SystemConstant;
import com.ezra.lua.RateLimit;
import com.ezra.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(SystemConstant.API_URL + "/limit")
public class RateLimitController {





    @GetMapping("")
    @RateLimit(key = "test",count = 10,time = 100)
    public Result test(){
        log.info("访问成功");
        return Result.SUCCESS;
    }
}
