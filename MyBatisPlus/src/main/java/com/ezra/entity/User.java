package com.ezra.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

      private String cUserId;


      /**
     * 用户名称
     */
      private String cName;

      /**
     * 用户状态，0删除1正常
     */
      private Integer status;

      private LocalDateTime createTime;

      private LocalDateTime updateTime;


      private Long cProvinceId;

      private Long cCityId;


}
