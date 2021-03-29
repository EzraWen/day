package com.ezra.controller;


import com.ezra.constant.SystemConstant;
import com.ezra.feign.TestFeign;
import com.ezra.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(SystemConstant.API_URL + "/test")
@RestController
public class TestController {

    @Autowired
    private TestFeign testFeign;

    @GetMapping("/fail")
    public Result test() {
        Result result = testFeign.runException();
        return result;
    }


}
