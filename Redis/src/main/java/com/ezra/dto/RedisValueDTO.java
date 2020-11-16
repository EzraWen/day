package com.ezra.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用于各种类型数据序列化测试
 */
@Data
public class RedisValueDTO {

    private String stringVal;

    private Integer integerVal;

    private Long longVal;

    private Boolean booleanVal;

    private BigDecimal bigDecimalVal;

    private Date dateVal;

    private LocalDateTime localDateTimeVal;

    private Map mapVal;

    private List listVal;

    private Set setVal;

    private Object objectVal;

    private LinkedList linkedListVal;

    private RedisObject redisObject;
}
