package com.ezra.entity;

import lombok.Data;

@Data
public class MomentCorpLimit {

    private Long id;

    private String wxCustomerId;

    private String corpId;

    private Integer count;

    private Boolean isDelete;
}
