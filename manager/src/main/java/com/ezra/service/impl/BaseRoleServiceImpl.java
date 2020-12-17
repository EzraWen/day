package com.ezra.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ezra.constant.AddOperation;
import com.ezra.constant.UpdateOperation;
import com.ezra.dto.RoleDTO;
import com.ezra.dto.RolePageDTO;
import com.ezra.entity.BaseRole;
import com.ezra.mapper.BaseRoleMapper;
import com.ezra.response.Result;
import com.ezra.service.IBaseRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ezra.util.ToolUtil;
import com.ezra.util.UserUtil;
import com.ezra.vo.RoleVO;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

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
            return Result.fail(String.format(AddOperation.TIP_MESSAGE_FAIL,"角色id不能有值"));
        }
        BaseRole role = new BaseRole();
        try {
            BeanUtils.copyProperties(dto,role);
        } catch (Exception e) {
            e.printStackTrace();
        }
        role.setStatus(Boolean.TRUE);
        LocalDateTime now = LocalDateTime.now();
        role.setCreateTime(now);
        role.setCreateUser(UserUtil.getCurrentUserName());
        role.setUpdateTime(now);
        role.setUpdateUser(UserUtil.getCurrentUserName());
        return this.save(role)? Result.SUCCESS:Result.BUSINESS_FAIL;
    }

    @Override
    public Result roleUpdate(RoleDTO dto) {
        BaseRole role = this.getById(dto.getRoleId());
        if (ToolUtil.isEmpty(role)) {
            return Result.fail(UpdateOperation.TIP_MESSAGE_NO_EXIST);
        }
        try {
            BeanUtils.copyProperties(dto,role);
        } catch (Exception e) {
            e.printStackTrace();
        }

        role.setUpdateTime(LocalDateTime.now());
        role.setUpdateUser(UserUtil.getCurrentUserName());

        return this.updateById(role) ? Result.SUCCESS : Result.BUSINESS_FAIL;
    }

    @Override
    public Result roleDelete(Long id) {
        BaseRole role = this.getById(id);
        if (ToolUtil.isEmpty(role)) {
            return Result.fail(UpdateOperation.TIP_MESSAGE_NO_EXIST);
        }

        role.setStatus(Boolean.FALSE);
        role.setUpdateTime(LocalDateTime.now());
        role.setUpdateUser(UserUtil.getCurrentUserName());

        //TODO 后面增加删除角色对应的     用户角色分配，角色菜单分配，角色数据分配  这几个表都是物理删除
        return this.updateById(role) ? Result.SUCCESS : Result.BUSINESS_FAIL;
    }

    @Override
    public Result roleGet(Long id) {
        BaseRole role = this.getById(id);
        if (ToolUtil.isEmpty(role)) {
            return Result.fail(UpdateOperation.TIP_MESSAGE_NO_EXIST);
        }

        RoleVO roleVO = new RoleVO();
        try {
            BeanUtils.copyProperties(role,roleVO);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //TODO  看使用旧项目的wrapper还是使用Mybatis 枚举转换
        return Result.data(roleVO);
    }

    @Override
    public Result rolePage(RolePageDTO dto) {
        IPage page = new Page<>(dto.getCurrent(), dto.getSize());
        baseRoleMapper.rolePage(page,dto);
        return Result.data(page);
    }
}
