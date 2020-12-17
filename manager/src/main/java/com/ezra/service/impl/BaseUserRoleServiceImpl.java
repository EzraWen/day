package com.ezra.service.impl;

import com.ezra.entity.BaseUserRole;
import com.ezra.mapper.BaseUserRoleMapper;
import com.ezra.service.IBaseUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author Ezra Wen
 * @since 2020-12-17
 */
@Service
public class BaseUserRoleServiceImpl extends ServiceImpl<BaseUserRoleMapper, BaseUserRole> implements IBaseUserRoleService {

}
