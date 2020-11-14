package com.ezra.response;

import lombok.Data;

@Data
public class Result<T> {

    private int code;
    private String msg;
    private T data;


    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Result setResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public Result setResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        return this;
    }

    public static final Result SUCCESS = new Result(MsgCode.CODE_SUCCESS,MsgCode.MSG_SUCCESS);
    public static final Result BUSINESS_FAIL = new Result(MsgCode.CODE_BUSINESS_FAIL,MsgCode.MSG_BUSINESS_FAIL);
    public static final Result UNAUTHORIZED = new Result(MsgCode.CODE_UNAUTHORIZED,MsgCode.MSG_UNAUTHORIZED);

}
