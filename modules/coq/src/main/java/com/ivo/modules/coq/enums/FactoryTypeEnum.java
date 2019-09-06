package com.ivo.modules.coq.enums;

import lombok.Getter;

/**
 * 工厂类型枚举
 * @Author: wj
 * @Date: 2019-08-20 14:10
 * @Version 1.0
 */
@Getter
public enum  FactoryTypeEnum {

    CELL((byte)1, "CELL"),
    MODULE((byte)2, "MODULE"),
    ARRAY((byte)3, "ARRAY")

    ;


    private Byte code;

    private String factory;

    FactoryTypeEnum(Byte code, String factory) {
        this.code = code;
        this.factory = factory;
    }
}
