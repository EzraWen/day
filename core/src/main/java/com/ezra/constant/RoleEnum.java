package com.ezra.constant;

import lombok.Getter;

@Getter
public enum RoleEnum {
    MENU(1,"菜单角色"),
    DATA(2,"数据角色");


    private int code;
    private String message;


    RoleEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
