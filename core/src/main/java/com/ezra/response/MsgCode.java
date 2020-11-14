package com.ezra.response;

public class MsgCode {

    /**
     * 请求成功
     */
    public static final int CODE_SUCCESS = 200;
    /**
     * 业务错误
     */
    public static final int CODE_BUSINESS_FAIL = 500;
    /**
     * 未授权请求
     */
    public static final int CODE_UNAUTHORIZED = 401;

    public static final String MSG_SUCCESS = "操作成功";
    public static final String MSG_BUSINESS_FAIL = "操作失败";
    public static final String MSG_UNAUTHORIZED  = "请求未授权";




}
