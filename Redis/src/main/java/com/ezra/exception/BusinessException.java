package com.ezra.exception;

import lombok.Data;

@Data
public class BusinessException extends Exception{

    private String msg;

    public BusinessException() {
        super();
    }

    public BusinessException(String msg){
        super(msg);
        this.msg = msg;
    }
}
