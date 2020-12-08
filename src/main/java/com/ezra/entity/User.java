package com.ezra.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Ezra Wen
 * @since 2020-12-08
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class User implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 用户id
     */
      private Long id;

      /**
     * 用户名称
     */
      private String name;

      /**
     * 用户状态，0删除1正常
     */
      private Integer status;


}
