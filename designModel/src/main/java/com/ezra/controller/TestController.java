package com.ezra.controller;

import com.ezra.service.impl.MomentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private MomentContext momentContext;


    @PostMapping("/hh")
    public void test(){
        momentContext.show();
    }
}
