package com.ezra.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ezra.entity.User;
import com.ezra.mapper.UserMapper;
import com.ezra.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>  implements IUserService {
}
