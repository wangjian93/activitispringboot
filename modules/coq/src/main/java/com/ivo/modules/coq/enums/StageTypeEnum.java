package com.ivo.modules.coq.enums;

import lombok.Getter;

/**
 * 新产品阶段枚举
 * @Author: wj
 * @Date: 2019-06-24 10:07
 * @Version 1.0
 */
@Getter
public enum StageTypeEnum {

    NPRB((byte)1, "NPRB"),

    DESIGN((byte)2, "DESIGN"),

    EVT((byte)3, "EVT"),

    DVT((byte)4, "DVT"),

    PVT((byte)5, "PVT");

    private Byte code;

    private String stage;

    StageTypeEnum(Byte code, String stage) {
        this.code = code;
        this.stage = stage;
    }
}
