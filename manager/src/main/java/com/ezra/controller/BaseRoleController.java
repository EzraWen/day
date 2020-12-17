package com.ezra.controller;


import com.ezra.constant.AddOperation;
import com.ezra.constant.UpdateOperation;
import com.ezra.dto.RoleDTO;
import com.ezra.dto.RolePageDTO;
import com.ezra.response.Result;
import com.ezra.service.IBaseRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author Ezra Wen
 * @since 2020-12-17
 */
@RestController
@RequestMapping("/role")
public class BaseRoleController {


    @Autowired
    private IBaseRoleService roleService;



    @PostMapping("")
    public Result insert(@RequestBody @Validated(AddOperation.class) RoleDTO dto){
        return roleService.roleInsert(dto);
    }

    @PutMapping("")
    public Result update(@RequestBody @Validated(UpdateOperation.class) RoleDTO dto){
        return roleService.roleUpdate(dto);
    }


    /**
     * 逻辑删除
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Long id){
        return roleService.roleDelete(id);

    }

    @GetMapping("/{id}")
    public Result get(@PathVariable("id") Long id){
        return roleService.roleGet(id);
    }

    @PostMapping("/list")
    public Result list(@RequestBody RolePageDTO dto){
        return roleService.rolePage(dto);
    }
}

