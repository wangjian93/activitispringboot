package com.ivo.common.enums;

import com.ivo.common.constant.StatusConst;
import lombok.Getter;

/**
 * 数据库字段状态枚举
 * @Author: wj
 * @Date: 2019-06-04 15:21
 * @Version 1.0
 */
@Getter
public enum StatusEnum {

    VALID(StatusConst.VALID, "有效"),
    DELETE(StatusConst.DELETE, "删除");

    private Byte code;

    private String message;

    StatusEnum(Byte code, String message) {
        this.code = code;
        this.message = message;
    }
}
