package com.ezra.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ezra.entity.MomentCorpLimit;
import com.ezra.mapper.MomentCorpLimitMapper;
import com.ezra.service.MomentCorpLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class MomentCorpLimitServiceImpl implements MomentCorpLimitService {

    @Autowired
    private MomentCorpLimitMapper momentCorpLimitMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean countHandler(String it) {

        MomentCorpLimit momentCorpLimit =  momentCorpLimitMapper.getByCustomerIdAndCorpIdForUpdate(it,"00000");

        if (Objects.isNull(momentCorpLimit)) {
            return false;
        }
        momentCorpLimit.setCount(momentCorpLimit.getCount() +1);
        momentCorpLimitMapper.updateById(momentCorpLimit);

        return true;
    }
}
