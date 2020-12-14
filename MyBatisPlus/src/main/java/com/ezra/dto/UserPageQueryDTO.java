package com.ezra.dto;

import lombok.Data;

@Data
public class UserPageQueryDTO extends BasePage {

    private String name;

    private Long provinceId;

    private Long cityId;
}
