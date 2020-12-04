package com.ezra.controller;

import com.ezra.constant.SystemConstant;
import com.ezra.entity.User;
import com.ezra.response.Result;
import com.ezra.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SystemConstant.API_URL + "/user")
public class UserController {


    @Autowired
    private IUserService userService;

    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        user.setStatus(1);
        boolean save = userService.save(user);
        return save ? Result.SUCCESS : Result.BUSINESS_FAIL;

    }
}
