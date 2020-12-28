package com.ezra.lua;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * lua限流注解 默认30分钟内接口最多100次访问
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {


    String key() default  "";

    int count() default 100;

    int time() default 1800;

}
