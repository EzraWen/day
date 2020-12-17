package com.ezra.service.impl;

import com.ezra.entity.BaseResource;
import com.ezra.mapper.BaseResourceMapper;
import com.ezra.service.IBaseResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据资源表 服务实现类
 * </p>
 *
 * @author Ezra Wen
 * @since 2020-12-17
 */
@Service
public class BaseResourceServiceImpl extends ServiceImpl<BaseResourceMapper, BaseResource> implements IBaseResourceService {

}
