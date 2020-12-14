package com.ezra.service;

import com.ezra.dto.UserPageQueryDTO;
import com.ezra.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ezra.response.Result;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Ezra Wen
 * @since 2020-12-08
 */
public interface IUserService extends IService<User> {

    Result pageQuery(UserPageQueryDTO dto);

    Result pageQuery2(UserPageQueryDTO dto);
}
