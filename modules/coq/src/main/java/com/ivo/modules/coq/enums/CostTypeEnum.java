package com.ivo.modules.coq.enums;

import lombok.Getter;

/**
 * 成本类型枚举
 * @Author: wj
 * @Date: 2019-06-24 10:49
 * @Version 1.0
 */
@Getter
public enum CostTypeEnum {

    /**
     * 必要花费
     */
    Necessary((byte)1, "necessaryCost"),

    /**
     * 多余花费
     */
    Unnecessary((byte)2, "unnecessaryCost"),

    /**
     * 预防成本
     */
    Precaution((byte)3, "precautionCost"),

    /**
     * 鉴定成本
     */
    Identify((byte)4, "identifyCost"),

    /**
     * 内损成本
     */
    InLoss((byte)5, "inLossCost"),

    /**
     * 外损成本
     */
    OutLoss((byte)6, "outLossCost"),


    /**
     * 直接材料成本
     */
    DirectMaterial((byte)7, "directMaterialCost"),

    /**
     * 治工具成本
     */
    Tool((byte)8, "toolCost"),

    /**
     * 验证费用
     */
    Validation((byte)8, "validationCost"),

    /**
     * 生产费用
     */
    Production((byte)9, "productionCost"),

    /**
     * 重工/报废费用
     */
    ReworkAndScrap((byte)10, "reworkAndScrapCost"),

    /**
     * 研发人员薪资费用
     */
    Salary((byte)11, "salaryCost"),

    /**
     * RMA成本
     */
    Rma((byte)12, "rmaCost"),

    /**
     * OBA成本
     */
    Oba((byte)13, "obaCost"),

    /**
     * 差旅费用
     */
    Travel((byte)14, "travelCost");


    private Byte code;

    private String costType;

    CostTypeEnum(Byte code, String costType) {
        this.code = code;
        this.costType = costType;
    }
}
