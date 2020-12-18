package com.ezra.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author Ezra Wen
 * @since 2020-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long roleId;

    /**
     * 类型，1菜单角色 2数据角色
     */
    private Integer type;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 1正常 0删除
     */
    @TableLogic
    private Boolean status;

    private LocalDateTime createTime;

    private String createUser;

    private LocalDateTime updateTime;

    private String updateUser;


}
