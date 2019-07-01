package com.ivo.modules.system.enums;

import lombok.Getter;

/**
 * @Author: wj
 * @Date: 2019-06-04 15:47
 * @Version 1.0
 */
@Getter
public enum MenuTypeEnum {

    TOP_LEVEL((byte)1, "一级菜单"),
    SUB_LEVEL((byte)2, "子级菜单"),
    NOT_MENU((byte)3, "不是菜单");

    private Byte code;

    private String message;

    MenuTypeEnum(Byte code, String message) {
        this.code = code;
        this.message = message;
    }
}
