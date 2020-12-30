package com.ezra.rule;

import com.ezra.entity.BaseApplicationRuleMultiSource;
import org.apache.ibatis.annotations.Param;

public interface BaseApplicationRuleMapper {

    BaseApplicationRuleMultiSource getByClassMethod(@Param("prefixCondition") String prefixCondition);
}
