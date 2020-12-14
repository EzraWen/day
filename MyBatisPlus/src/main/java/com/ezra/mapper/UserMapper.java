package com.ezra.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ezra.dto.UserPageQueryDTO;
import com.ezra.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ezra.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Ezra Wen
 * @since 2020-12-08
 */
public interface UserMapper extends BaseMapper<User> {

    IPage<UserVO> pageQuery(IPage page, @Param("dto") UserPageQueryDTO dto);

    List<UserVO> pageQuery2(@Param("dto") UserPageQueryDTO dto);

    int pageQueryCount(@Param("dto") UserPageQueryDTO dto);
}
