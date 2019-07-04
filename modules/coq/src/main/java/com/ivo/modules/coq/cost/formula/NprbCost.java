package com.ivo.modules.coq.cost.formula;

import com.ivo.modules.coq.cost.DoubleUtil;
import com.ivo.modules.coq.domain.PhaseCostDetail;

import java.math.BigDecimal;

/**
 * 获取NPRB阶段的成本数据
 * @Author: wj
 * @Date: 2019-06-24 14:19
 * @Version 1.0
 */
public class NprbCost implements Cost {

    /**
     * NPRB阶段直接材料成本接口
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    @Override
    public Double getDirectMaterialCost(String projectName, String phase) {
        //TODO... NPRB阶段直接材料成本接口
        return null;
    }

    /**
     * NPRB阶段治工具接口
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    @Override
    public Double getToolCost(String projectName, String phase) {
        //TODO... NPRB阶段治工具接口
        return null;
    }

    /**
     * NPRB阶段验证费用成本接口
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    @Override
    public Double getValidationCost(String projectName, String phase) {
        //TODO... NPRB阶段验证费用成本接口
        return null;
    }

    /**
     * NPRB阶段生产费用成本接口
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    @Override
    public Double getProductionCost(String projectName, String phase) {
        //TODO... NPRB阶段生产费用成本接口
        return null;
    }

    /**
     * NPRB阶段重工报废费用成本接口
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    @Override
    public Double getReworkAndScrapCost(String projectName, String phase) {
        //TODO... NPRB阶段重工报废费用成本接口
        return null;
    }

    /**
     * NPRB阶段研发人员薪资成本接口
     * @param projectName
     * @param phase
     * @return
     */
    @Override
    public Double getSalaryCost(String projectName, String phase) {
        //TODO... NPRB阶段研发人员薪资成本接口
        return 7586.20;
    }

    /**
     * NPRB阶段RMA成本接口
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    @Override
    public Double getRmaCost(String projectName, String phase) {
        //TODO... NPRB阶段RMA成本接口
        return null;
    }

    /**
     * NPRB阶段OBA成本接口
     * @param projectName
     * @param phase
     * @return
     */
    @Override
    public Double getObaCost(String projectName, String phase) {
        //TODO... NPRB阶段OBA成本接口
        return null;
    }

    /**
     * NPRB阶段差旅费接口
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    @Override
    public Double getTravelCost(String projectName, String phase) {
        //TODO... NPRB阶段差旅费接口
        return 0D;
    }

    /**
     * NPRB阶段预防成本计算
     * 预防成本 (研发人员薪资 + 差旅费)
     * @param phaseCostDetail 机种成本明细
     * @param phase 阶段
     * @return
     */
    @Override
    public Double computePrecautionCost(PhaseCostDetail phaseCostDetail, String phase) {
        //TODO... NPRB阶段预防成本计算
        // 预防成本 (研发人员薪资 + 差旅费)
        Double salaryCost = phaseCostDetail.getSalaryCost();
        Double travelCost = phaseCostDetail.getTravelCost();

        return DoubleUtil.sum(salaryCost, travelCost);
    }

    /**
     * NPRB阶段鉴定成本计算
     * @param phaseCostDetail 机种成本明细
     * @param phase 阶段
     * @return
     */
    @Override
    public Double computeIdentifyCost(PhaseCostDetail phaseCostDetail, String phase) {
        //TODO... NPRB阶段鉴定成本计算
        return null;
    }

    /**
     * NPRB阶段内损成本计算
     * @param phaseCostDetail 机种成本明细
     * @param phase 阶段
     * @return
     */
    @Override
    public Double computeInLossCost(PhaseCostDetail phaseCostDetail, String phase) {
        //TODO.. NPRB阶段内损成本计算
        return null;
    }

    /**
     * NPRB阶段外损成本计算
     * @param phaseCostDetail 机种成本明细
     * @param phase 阶段
     * @return
     */
    @Override
    public Double computeOutLossCost(PhaseCostDetail phaseCostDetail, String phase) {
        //TODO... NPRB阶段外损成本计算
        return null;
    }
}
