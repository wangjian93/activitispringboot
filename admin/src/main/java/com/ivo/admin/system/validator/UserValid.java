package com.ivo.admin.system.validator;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 用户数据效验
 * @Author: wj
 * @Date: 2019-07-15 13:20
 * @Version 1.0
 */
@Data
public class UserValid {

    @NotEmpty(message = "用户ID（员工工号）不能为空")
    private String userid;

    @NotEmpty(message = "用户名不能为空")
    private String username;

    @NotEmpty(message = "密码不能为空")
    private String password;

    @NotEmpty(message = "密码确认不能为空")
    private String passwordConfirm;
}
