package com.ezra.controller;

import com.ezra.constant.SystemConstant;
import com.ezra.dto.IdDTO;
import com.ezra.entity.User;
import com.ezra.mapper.UserEntityMapper;
import com.ezra.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SystemConstant.API_URL + "/druid")
public class UserController {

    @Autowired
    private UserEntityMapper userEntityMapper;


    @GetMapping("/get")
    public Result get(@RequestParam("id") Long id){
        User user = userEntityMapper.getById(id);
        return Result.data(user);
    }

    @GetMapping("/get2")
    public Result get2(@RequestParam("id") Long id){
        IdDTO idDTO = new IdDTO();
        idDTO.setId(id);
        User user = userEntityMapper.getByIdDTO(idDTO);
        return Result.data(user);
    }
}
