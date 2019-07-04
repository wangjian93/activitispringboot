package com.ivo.modules.system.enums;

import lombok.Getter;

/**
 * @Author: wj
 * @Date: 2019-06-04 15:47
 * @Version 1.0
 */
@Getter
public enum MenuTypeEnum {

    MENU((byte)1, "菜单"),
    BUTTON((byte)2, "按钮"),

    ;
    private Byte code;

    private String message;

    MenuTypeEnum(Byte code, String message) {
        this.code = code;
        this.message = message;
    }
}
