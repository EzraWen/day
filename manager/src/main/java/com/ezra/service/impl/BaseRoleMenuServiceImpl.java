package com.ezra.service.impl;

import com.ezra.entity.BaseRoleMenu;
import com.ezra.mapper.BaseRoleMenuMapper;
import com.ezra.service.IBaseRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色菜单关联表 服务实现类
 * </p>
 *
 * @author Ezra Wen
 * @since 2020-12-17
 */
@Service
public class BaseRoleMenuServiceImpl extends ServiceImpl<BaseRoleMenuMapper, BaseRoleMenu> implements IBaseRoleMenuService {

}
