package com.ezra.controller;

import com.ezra.constant.SystemConstant;
import com.ezra.document.UserDetailDocument;
import com.ezra.response.Result;
import com.ezra.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SystemConstant.API_URL +"/es")
public class UserController {


    @Autowired
    private UserDetailService userDetailService;


    @PostMapping("/test")
    public Result test(@RequestBody UserDetailDocument document){
        userDetailService.save(document);
        return Result.SUCCESS;
    }
}
