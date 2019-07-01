package com.ivo.modules.coq.cost.formula;

import com.ivo.modules.coq.domain.PhaseCostDetail;

import java.math.BigDecimal;

/**
 * 获取NPRB阶段的成本数据
 * @Author: wj
 * @Date: 2019-06-24 14:19
 * @Version 1.0
 */
public class NprbCost implements Cost {

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
        return 7586.20;
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
        Double travelCost = phaseCostDetail.getTravelCost();

        if(salaryCost == null && travelCost == null) {
            return null;
        }

        BigDecimal bigDecimal = new BigDecimal(0);
        if(salaryCost != null ) {
            bigDecimal = bigDecimal.add(new BigDecimal(salaryCost));
        }

        if(travelCost != null) {
            bigDecimal = bigDecimal.add(new BigDecimal(travelCost));
        }
        return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
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
