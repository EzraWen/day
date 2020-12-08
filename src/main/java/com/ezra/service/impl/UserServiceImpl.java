package com.ezra.service.impl;

import com.ezra.entity.User;
import com.ezra.mapper.UserMapper;
import com.ezra.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Ezra Wen
 * @since 2020-12-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
