package com.ezra.service;

import com.ezra.dto.RoleDTO;
import com.ezra.dto.RolePageDTO;
import com.ezra.entity.BaseRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ezra.response.Result;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author Ezra Wen
 * @since 2020-12-17
 */
public interface IBaseRoleService extends IService<BaseRole> {

    Result roleInsert(RoleDTO dto);

    Result roleUpdate(RoleDTO dto);

    Result roleDelete(Long id);

    Result roleGet(Long id);

    Result rolePage(RolePageDTO dto);
}
