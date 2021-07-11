package com.ezra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ezra.entity.MomentCorpLimit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MomentCorpLimitMapper extends BaseMapper<MomentCorpLimit> {

    MomentCorpLimit getByCustomerIdAndCorpIdForUpdate(@Param("customerId") String customerId, @Param("corpId") String corpId);
}
