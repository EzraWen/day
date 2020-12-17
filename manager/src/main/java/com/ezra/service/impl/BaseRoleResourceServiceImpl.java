package com.ezra.service.impl;

import com.ezra.entity.BaseRoleResource;
import com.ezra.mapper.BaseRoleResourceMapper;
import com.ezra.service.IBaseRoleResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色资源关联表 服务实现类
 * </p>
 *
 * @author Ezra Wen
 * @since 2020-12-17
 */
@Service
public class BaseRoleResourceServiceImpl extends ServiceImpl<BaseRoleResourceMapper, BaseRoleResource> implements IBaseRoleResourceService {

}
