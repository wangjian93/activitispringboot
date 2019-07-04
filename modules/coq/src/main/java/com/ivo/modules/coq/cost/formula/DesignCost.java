package com.ivo.modules.coq.cost.formula;

import com.ivo.modules.coq.cost.DoubleUtil;
import com.ivo.modules.coq.domain.PhaseCostDetail;

import java.math.BigDecimal;

/**
 * 获取Design阶段的成本数据
 * @Author: wj
 * @Date: 2019-06-24 14:42
 * @Version 1.0
 */
public class DesignCost implements Cost {

    /**
     * Design阶段直接材料成本接口
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    @Override
    public Double getDirectMaterialCost(String projectName, String phase) {
        //TODO... Design阶段直接材料成本接口
        return null;
    }

    /**
     * Design阶段治工具成本接口
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    @Override
    public Double getToolCost(String projectName, String phase) {
        //TODO... Design阶段治工具成本接口
        return 961200.00;
    }

    /**
     * Design阶段验证费用成本接口
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    @Override
    public Double getValidationCost(String projectName, String phase) {
        //TODO... Design阶段验证费用成本接口
        return 9986.30;
    }

    /**
     * Design阶段生产费用成本接口
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    @Override
    public Double getProductionCost(String projectName, String phase) {
        //TODO... Design阶段生产费用成本接口
        return null;
    }

    /**
     * Design阶段重工报废费用成本接口
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    @Override
    public Double getReworkAndScrapCost(String projectName, String phase) {
        // TODO... Design阶段重工报废费用成本接口
        return null;
    }

    /**
     * Design阶段研发人员薪资成本接口
     * @param projectName
     * @param phase
     * @return
     */
    @Override
    public Double getSalaryCost(String projectName, String phase) {
        //TODO... Design阶段研发人员薪资成本接口
        return 17068.97;
    }

    /**
     * Design阶段RMA成本接口
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    @Override
    public Double getRmaCost(String projectName, String phase) {
        //TODO... Design阶段RMA成本接口
        return null;
    }

    /**
     * Design阶段OBA成本接口
     * @param projectName
     * @param phase
     * @return
     */
    @Override
    public Double getObaCost(String projectName, String phase) {
        //TODO... Design阶段OBA成本接口
        return null;
    }

    /**
     * Design阶段差旅费成本接口
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    @Override
    public Double getTravelCost(String projectName, String phase) {
        //TODO... Design阶段差旅费成本接口
        return null;
    }

    /**
     * Design阶段预防成本计算
     * 预防成本 （治具工具成本 + 研发人员薪资成本）
     * @param phaseCostDetail 机种成本明细
     * @param phase 阶段
     * @return
     */
    @Override
    public Double computePrecautionCost(PhaseCostDetail phaseCostDetail, String phase) {
        //TODO... Design阶段预防成本计算
        // 预防成本 （治具工具成本 + 研发人员薪资成本）
        Double toolCost = phaseCostDetail.getToolCost();
        Double salaryCost = phaseCostDetail.getSalaryCost();

        return DoubleUtil.sum(toolCost, salaryCost);
    }

    /**
     * Design阶段鉴定成本计算
     * 鉴定成本 (验证费用成本)
     * @param phaseCostDetail 机种成本明细
     * @param phase 阶段
     * @return
     */
    @Override
    public Double computeIdentifyCost(PhaseCostDetail phaseCostDetail, String phase) {
        //TODO... Design阶段鉴定成本计算
        // 鉴定成本 (验证费用成本)
        Double validationCost = phaseCostDetail.getValidationCost();

        return DoubleUtil.sum(validationCost);
    }

    /**
     * Design阶段内损成本计算
     * 内损成本 (差旅费成本)
     * @param phaseCostDetail 机种成本明细
     * @param phase 阶段
     * @return
     */
    @Override
    public Double computeInLossCost(PhaseCostDetail phaseCostDetail, String phase) {
        //TODO... Design阶段内损成本计算
        // 内损成本 (差旅费成本)
        return phaseCostDetail.getTravelCost();
    }

    /**
     * Design阶段外损成本计算
     * @param phaseCostDetail 机种成本明细
     * @param phase 阶段
     * @return
     */
    @Override
    public Double computeOutLossCost(PhaseCostDetail phaseCostDetail, String phase) {
        //TODO... Design阶段外损成本计算
        return null;
    }
}
