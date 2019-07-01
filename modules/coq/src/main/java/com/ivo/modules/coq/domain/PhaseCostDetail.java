package com.ivo.modules.coq.domain;

import lombok.Data;

/**
 * 新产品阶段的费用明细
 * @Author: wj
 * @Date: 2019-06-24 10:18
 * @Version 1.0
 */
@Data
public class PhaseCostDetail {

    /**
     * 阶段
     */
    private String phase;

    /**
     * 直接材料成本
     */
    private Double directMaterialCost;

    /**
     * 治工具成本
     */
    private Double toolCost;

    /**
     * 验证费用
     */
    private Double validationCost;

    /**
     * 生产费用
     */
    private Double productionCost;

    /**
     * 重工/报废费用
     */
    private Double reworkAndScrapCost;

    /**
     * 研发人员薪资费用
     */
    private Double salaryCost;

    /**
     * RMA成本
     */
    private Double rmaCost;

    /**
     * OBA成本
     */
    private Double obaCost;

    /**
     * 差旅费用
     */
    private Double travelCost;

    public PhaseCostDetail(String phase) {
        this.phase = phase;
    }
}
