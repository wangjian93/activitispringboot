package com.ivo.modules.coq.domain;

import lombok.Data;

import java.util.List;

/**
 * 机种成本
 * @Author: wj
 * @Date: 2019-06-24 11:16
 * @Version 1.0
 */
@Data
public class ProjectCost3 {

    /**
     * 机种
     */
    private String projectName;

    /**
     * 各阶段成本明细
     */
    private List<PhaseCostDetail> phaseCostDetails;

    /**
     * 成本的二级科目
     */
    private List<PhaseCost> phaseCosts;


    /**
     * 必要花费
     * 预防成本 + 鉴定成本
     */
    private Double necessaryCost;

    /**
     * 多余花费
     * 内损成本 + 外损成本
     */
    private Double unnecessaryCost;

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

    public ProjectCost3(String projectName, List<PhaseCost> phaseCosts, List<PhaseCostDetail> phaseCostDetails) {
        this.projectName = projectName;
        this.phaseCosts = phaseCosts;
        this.phaseCostDetails = phaseCostDetails;
    }
}
