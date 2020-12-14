package com.ezra.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserVO {

    private Long id;
    private String userId;
    private String userName;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private Long provinceId;
    private Long cityId;

}
