package com.ezra.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户角色关联表
 * </p>
 *
 * @author Ezra Wen
 * @since 2020-12-17
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class BaseUserRole implements Serializable {

    private static final long serialVersionUID=1L;

      private Long id;

    private Long userId;

    private Long roleId;

    private LocalDateTime createTime;

    private Long createUserId;

    private LocalDateTime updateTime;

    private Long updateUserId;


}
