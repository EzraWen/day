package com.ezra.controller;

import com.ezra.constant.SystemConstant;
import com.ezra.dto.UserPageQueryDTO;
import com.ezra.entity.User;
import com.ezra.response.Result;
import com.ezra.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


    @PostMapping("/get")
    public Result get(@RequestParam("id") Long id) {
        return Result.data(userService.getById(id));
    }

    @PostMapping("/page")
    public Result page(@RequestBody UserPageQueryDTO dto) {
        return userService.pageQuery(dto);
    }

    @PostMapping("/page2")
    public Result page2(@RequestBody UserPageQueryDTO dto) {
        return userService.pageQuery2(dto);
    }
}
