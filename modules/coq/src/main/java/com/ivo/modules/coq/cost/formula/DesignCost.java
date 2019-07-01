package com.ivo.modules.coq.cost.formula;

import com.ivo.modules.coq.domain.PhaseCostDetail;

import java.math.BigDecimal;

/**
 * 获取Design阶段的成本数据
 * @Author: wj
 * @Date: 2019-06-24 14:42
 * @Version 1.0
 */
public class DesignCost implements Cost {

    @Override
    public Double getDirectMaterialCost(String projectName, String phase) {
        return null;
    }

    @Override
    public Double getToolCost(String projectName, String phase) {
        return 961200.00;
    }

    @Override
    public Double getValidationCost(String projectName, String phase) {
        return 9986.30;
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
        return 17068.97;
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

        Double salaryCost = phaseCostDetail.getSalaryCost();
        Double toolCost = phaseCostDetail.getToolCost();

        if(salaryCost == null && toolCost == null) {
            return null;
        }

        BigDecimal bigDecimal = new BigDecimal(0);
        if(salaryCost != null ) {
            bigDecimal = bigDecimal.add(new BigDecimal(salaryCost));
        }

        if(toolCost != null) {
            bigDecimal = bigDecimal.add(new BigDecimal(toolCost));
        }
        return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    @Override
    public Double computeIdentifyCost(PhaseCostDetail phaseCostDetail, String phase) {
        return phaseCostDetail.getValidationCost();
    }

    @Override
    public Double computeInLossCost(PhaseCostDetail phaseCostDetail, String phase) {
        return phaseCostDetail.getTravelCost();
    }

    @Override
    public Double computeOutLossCost(PhaseCostDetail phaseCostDetail, String phase) {
        return null;
    }
}
