package com.ezra.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ezra.entity.MomentCorpLimit;
import com.ezra.mapper.MomentCorpLimitMapper;
import com.ezra.service.MomentCorpLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MomentCorpLimitServiceImpl implements MomentCorpLimitService {

    @Autowired
    private MomentCorpLimitMapper momentCorpLimitMapper;

    @Override
    public boolean countHandler(String it) {


        MomentCorpLimit momentCorpLimit = momentCorpLimitMapper.selectOne(new QueryWrapper<MomentCorpLimit>().lambda()
                .eq(MomentCorpLimit::getWxCustomerId, it)
                .eq(MomentCorpLimit::getCorpId, "00000")
                .eq(MomentCorpLimit::getIsDelete, Boolean.FALSE));

        if (Objects.isNull(momentCorpLimit)) {
            return false;
        }
        momentCorpLimit.setCount(momentCorpLimit.getCount() +1);
        momentCorpLimitMapper.updateById(momentCorpLimit);

        return true;
    }
}
