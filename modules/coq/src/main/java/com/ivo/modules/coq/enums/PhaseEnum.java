package com.ivo.modules.coq.enums;

import lombok.Getter;

/**
 * 新产品阶段枚举
 * @Author: wj
 * @Date: 2019-06-24 10:07
 * @Version 1.0
 */
@Getter
public enum PhaseEnum {

    NPRB((byte)1, "NPRB"),

    Design((byte)2, "Design"),

    EVT((byte)3, "EVT"),

    DVT((byte)4, "DVT"),

    PVT((byte)5, "PVT");

    private Byte code;

    private String phase;

    PhaseEnum(Byte code, String phase) {
        this.code = code;
        this.phase = phase;
    }
}
