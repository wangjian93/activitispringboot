package com.ivo.modules.system.enums;

import lombok.Getter;

/**
 * 菜单类型枚举
 * @Author: wj
 * @Date: 2019-06-04 15:47
 * @Version 1.0
 */
@Getter
public enum MenuTypeEnum {

    BUTTON((byte)0, "按钮"),
    MENU((byte)1, "菜单"),


    ;
    private Byte code;

    private String message;

    MenuTypeEnum(Byte code, String message) {
        this.code = code;
        this.message = message;
    }
}
