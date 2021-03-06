package com.ezra.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色资源关联表
 * </p>
 *
 * @author Ezra Wen
 * @since 2020-12-17
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class BaseRoleResource implements Serializable {

    private static final long serialVersionUID=1L;

      private Long id;

    private Long roleId;

    private Long resourceId;

    private LocalDateTime createTime;

    private Long createUserId;

    private LocalDateTime updateTime;

    private Long updateUserId;


}
