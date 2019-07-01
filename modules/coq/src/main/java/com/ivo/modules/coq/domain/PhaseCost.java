package com.ivo.modules.coq.domain;

import lombok.Data;

/**
 * 新产品阶段的费用二级科目
 * @Author: wj
 * @Date: 2019-06-24 11:36
 * @Version 1.0
 */
@Data
public class PhaseCost {

    /**
     * 阶段
     */
    private String phase;

    /**
     * 预防成本
     */
    private Double precautionCost;

    /**
     * 鉴定成本
     */
    private Double identifyCost;

    /**
     * 内损成本
     */
    private Double inLossCost;

    /**
     * 外损成本
     */
    private Double outLossCost;

    public PhaseCost(String phase) {
        this.phase = phase;
    }
}
