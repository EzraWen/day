package com.ezra.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 数据资源表
 * </p>
 *
 * @author Ezra Wen
 * @since 2020-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long resourceId;

    /**
     * 名称
     */
    private String resourceName;

    /**
     * 父id
     */
    private Long pid;

    /**
     * 资源类型，控制是什么数据
     */
    private String resourceType;

    /**
     * 资源条件
     */
    private String condition;

    private Boolean status;

    private LocalDateTime createTime;

    private String createUser;

    private LocalDateTime updateTime;

    private String updateUser;


}
