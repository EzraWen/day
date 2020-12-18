package com.ezra.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author Ezra Wen
 * @since 2020-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long menuId;

    private String menuName;

    private Long pid;

    /**
     * 是否显示，按钮默认不显示
     */
    private Boolean isShow;

    /**
     * 1菜单 2按钮，按钮用于前端按钮权限判断
     */
    private Boolean type;

    private String path;

    /**
     * 1正常 2删除
     */
    private Boolean status;

    private LocalDateTime createTime;

    private String createUser;

    private LocalDateTime updateTime;

    private String updateUser;


}
