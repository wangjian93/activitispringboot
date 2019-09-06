package com.ivo.admin.system.validator;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 角色数据效验
 * @Author: wj
 * @Date: 2019-07-15 17:41
 * @Version 1.0
 */
@Data
public class RoleValid {

    @NotEmpty(message = "角色名不能为空")
    private String name;

    @NotEmpty(message = "角色描述名称不能为空")
    private String title;
}
