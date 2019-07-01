package com.ivo.modules.coq.cost.formula;

import com.ivo.modules.coq.domain.PhaseCostDetail;

/**
 * 获取Pvt阶段的成本数据
 * @Author: wj
 * @Date: 2019-06-24 14:46
 * @Version 1.0
 */
public class PvtCost implements Cost {

    @Override
    public Double getDirectMaterialCost(String projectName, String phase) {
        return null;
    }

    @Override
    public Double getToolCost(String projectName, String phase) {
        return null;
    }

    @Override
    public Double getValidationCost(String projectName, String phase) {
        return null;
    }

    @Override
    public Double getProductionCost(String projectName, String phase) {
        return null;
    }

    @Override
    public Double getReworkAndScrapCost(String projectName, String phase) {
        return null;
    }

    @Override
    public Double getSalaryCost(String projectName, String phase) {
        return null;
    }

    @Override
    public Double getRmaCost(String projectName, String phase) {
        return null;
    }

    @Override
    public Double getObaCost(String projectName, String phase) {
        return null;
    }

    @Override
    public Double getTravelCost(String projectName, String phase) {
        return null;
    }

    @Override
    public Double computePrecautionCost(PhaseCostDetail phaseCostDetail, String phase) {
        return null;
    }

    @Override
    public Double computeIdentifyCost(PhaseCostDetail phaseCostDetail, String phase) {
        return null;
    }

    @Override
    public Double computeInLossCost(PhaseCostDetail phaseCostDetail, String phase) {
        return null;
    }

    @Override
    public Double computeOutLossCost(PhaseCostDetail phaseCostDetail, String phase) {
        return null;
    }
}
