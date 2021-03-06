package com.ezra.constant;

import com.google.common.collect.Sets;

import java.util.Set;

public interface BaseEnum<E extends Enum<E>, T> {

    //接口实现类装载容器，方便快速获取全部子类，所有实现子类必须使用静态块将其注册进来
//    Set<Class<?>> subClass = Sets.newConcurrentHashSet();

    /**
     * 真正与数据库进行映射的值
     *
     * @return
     */
    T getValue();

    /**
     * 显示的信息
     *
     * @return
     */
    String getDisplayName();
}
