package com.ezra.constant;

public class RabbitMQConstant {


    public static final String QUEUE1 = "queue1";
    public static final String QUEUE2 = "queue2";


    public static final  String FANOUT_EXCHANGE = "fanout";     //广播
    public static final  String TOPIC_EXCHANGE = "topic";       //通配符
    public static final  String DIRECT_EXCHANGE = "direct";     //路由
    public static final  String HEADERS_EXCHANGE = "headers";   //消息头匹配
}
