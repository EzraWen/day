package com.ezra.service.impl;

import com.ezra.constant.AddOperation;
import com.ezra.dto.RoleDTO;
import com.ezra.entity.BaseRole;
import com.ezra.mapper.BaseRoleMapper;
import com.ezra.response.Result;
import com.ezra.service.IBaseRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ezra.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Ezra Wen
 * @since 2020-12-17
 */
@Service
public class BaseRoleServiceImpl extends ServiceImpl<BaseRoleMapper, BaseRole> implements IBaseRoleService {

    @Autowired
    private BaseRoleMapper baseRoleMapper;

    @Override
    public Result roleInsert(RoleDTO dto) {
        Long roleId = dto.getRoleId();
        if (ToolUtil.isNotEmpty(roleId)) {
            return Result.fail(String.format(AddOperation.TIP_MESSAGE,"角色id不能有值"));
        }


        return null;
    }
}
