package com.ezra.constant;

import lombok.Getter;

@Getter
public enum SizeEnum implements BaseEnum<SizeEnum,Integer> {


    X_SMALL(1,"x-small"),
    SMALL(2,"small"),
    MEDIUM(3,"medium"),
    LARGE(4,"large"),
    X_LARGE(5,"x-large");


    private int code;
    private String message;


    SizeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getValue() {
        return code;
    }

    @Override
    public String getDisplayName() {
        return message;
    }
}
