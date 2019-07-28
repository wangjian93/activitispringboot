package com.ivo.modules.system.enums;

import lombok.Getter;

/**
 * 用户类型枚举
 * @Author: wj
 * @Date: 2019-07-15 13:29
 * @Version 1.0
 */
@Getter
public enum  UserTypeEnum {

    EMPLOYEE((byte)1, "员工用户"),
    SYSTEM((byte)1, "系统用户");

    private Byte code;
    private String message;

    UserTypeEnum(Byte code, String message) {
        this.code = code;
        this.message = message;
    }
}
