package com.ezra.dto;

import com.ezra.constant.UpdateOperation;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RoleDTO {

    @NotNull(message = "角色id不能为空",groups = UpdateOperation.class)
    private Long roleId;

    /**
     * 类型，1菜单角色 2数据角色
     */
    @NotNull(message = "角色类型不能为空")
    @Range(min = 1,max = 2,message = "角色类型范围{1:菜单角色,2:数据角色}")
    private Integer type;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    private String roleName;
}
