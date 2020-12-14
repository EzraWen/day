package com.ezra.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ezra.dto.UserPageQueryDTO;
import com.ezra.entity.User;
import com.ezra.mapper.UserMapper;
import com.ezra.response.Result;
import com.ezra.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ezra.vo.UserVO;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Result pageQuery(UserPageQueryDTO dto) {
        IPage page = new Page<>(dto.getCurrent(), dto.getSize());
        this.baseMapper.pageQuery(page,dto);
        return Result.data(page);
    }

    @Override
    public Result pageQuery2(UserPageQueryDTO dto) {

        IPage page = new Page<>(dto.getCurrent(), dto.getSize());

        List<UserVO> records = this.baseMapper.pageQuery2(dto);
        int count = this.baseMapper.pageQueryCount(dto);


        page.setRecords(records);
        page.setTotal(count);
        return Result.data(page);
    }
}
