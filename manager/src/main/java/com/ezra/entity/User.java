package com.ezra.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author Ezra Wen
 * @since 2020-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String userId;

    private String name;

    private String provinceId;

    private String cityId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Boolean status;


}
