package com.ivo.modules.system.enums;

import lombok.Getter;

/**
 * 用户状态枚举
 * @Author: wj
 * @Date: 2019-07-11 13:46
 * @Version 1.0
 */
@Getter
public enum  UserStatusEnum {

    DISABLED((byte) 0, "冻结"),
    UNDISABLED((byte)1, "正常")

    ;

    private Byte code;

    private String message;

    UserStatusEnum(Byte code, String message) {
        this.code = code;
        this.message = message;
    }
}
