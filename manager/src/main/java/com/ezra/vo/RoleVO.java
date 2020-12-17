package com.ezra.vo;

import java.time.LocalDateTime;

public class RoleVO {

    private Long roleId;

    /**
     * 类型，1菜单角色 2数据角色
     */
    private Integer type;

    private String typeName;

    /**
     * 角色名称
     */
    private String roleName;

    private LocalDateTime createTime;

    private String createUser;

    private LocalDateTime updateTime;

    private String updateUser;
}
