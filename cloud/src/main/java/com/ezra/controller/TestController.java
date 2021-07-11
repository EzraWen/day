package com.ezra.controller;


import com.ezra.constant.SystemConstant;
import com.ezra.feign.TestFeign;
import com.ezra.response.MsgCode;
import com.ezra.response.Result;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(SystemConstant.API_URL + "/test")
@RestController
public class TestController {

    @Autowired
    private TestFeign testFeign;

    @HystrixCommand(fallbackMethod = "defaultFallback")
    @GetMapping("/fail")
    public Result test() {
        Result result = testFeign.runException();
        return result;
    }


    public Result defaultFallback(){
        return new Result<>(MsgCode.CODE_BUSINESS_FAIL,"这里是私有的返回结果");
    }


}
