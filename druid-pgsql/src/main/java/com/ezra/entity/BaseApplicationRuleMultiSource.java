package com.ezra.entity;

import lombok.Data;

@Data
public class BaseApplicationRuleMultiSource {

    private String objectName;
    private Integer seqNo;
    private String source;
    private Integer lKey;
    private String conditions;

}
