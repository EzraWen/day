package com.ezra.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ezra.dto.RolePageDTO;
import com.ezra.entity.BaseRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ezra.vo.RoleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author Ezra Wen
 * @since 2020-12-17
 */
public interface BaseRoleMapper extends BaseMapper<BaseRole> {

    List<RoleVO> rolePage(IPage page, @Param("dto") RolePageDTO dto);
}
