package com.ivo.modules.coq.cost.formula;

import com.ivo.modules.coq.domain.PhaseCostDetail;

/**
 * 获取各种成本数据
 * @Author: wj
 * @Date: 2019-06-24 14:16
 * @Version 1.0
 */
public interface Cost {

    /**
     * 获取直接材料成本
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    Double getDirectMaterialCost(String projectName, String phase);

    /**
     * 获取治工具成本
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    Double getToolCost(String projectName, String phase);

    /**
     * 获取验证费用
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    Double getValidationCost(String projectName, String phase);

    /**
     * 获取生产费用
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    Double getProductionCost(String projectName, String phase);

    /**
     * 获取重工/报废费用
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    Double getReworkAndScrapCost(String projectName, String phase);

    /**
     * 获取研发人员薪资
     * @param projectName
     * @return
     */
    Double getSalaryCost(String projectName, String phase);


    /**
     * 获取RMA成本
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    Double getRmaCost(String projectName, String phase);

    /**
     * 获取OBA成本
     * @param projectName
     * @return
     */
    Double getObaCost(String projectName, String phase);

    /**
     * 获取差旅费用
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    Double getTravelCost(String projectName, String phase);

    /**
     * 计算预防成本
     * @param phaseCostDetail 机种成本明细
     * @param phase 阶段
     * @return
     */
    Double computePrecautionCost(PhaseCostDetail phaseCostDetail, String phase);

    /**
     * 计算鉴定成本
     * @param phaseCostDetail 机种成本明细
     * @param phase 阶段
     * @return
     */
    Double computeIdentifyCost(PhaseCostDetail phaseCostDetail, String phase);


    /**
     * 计算内损成本
     * @param phaseCostDetail 机种成本明细
     * @param phase 阶段
     * @return
     */
    Double computeInLossCost(PhaseCostDetail phaseCostDetail, String phase);

    /**
     * 计算外损成本
     * @param phaseCostDetail 机种成本明细
     * @param phase 阶段
     * @return
     */
    Double computeOutLossCost(PhaseCostDetail phaseCostDetail, String phase);
}
