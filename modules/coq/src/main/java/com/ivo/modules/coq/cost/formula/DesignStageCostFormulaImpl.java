package com.ivo.modules.coq.cost.formula;

import com.ivo.modules.coq.cost.DoubleUtil;
import com.ivo.modules.coq.domain.ProjectStageCost;

/**
 * 获取Design阶段的成本数据
 * @Author: wj
 * @Date: 2019-06-24 14:42
 * @Version 1.0
 */
public class DesignStageCostFormulaImpl implements StageCostFormula {

    /**
     * Design阶段直接材料成本接口
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    @Override
    public Double getDirectMaterialCost(String projectName, String phase, ProjectStageCost projectStageCost) {
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
    public Double getToolCost(String projectName, String phase, ProjectStageCost projectStageCost) {
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
    public Double getValidationCost(String projectName, String phase, ProjectStageCost projectStageCost) {
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
    public Double getProductionCost(String projectName, String phase, ProjectStageCost projectStageCost) {
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
    public Double getReworkAndScrapCost(String projectName, String phase, ProjectStageCost projectStageCost) {
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
    public Double getSalaryCost(String projectName, String phase, ProjectStageCost projectStageCost) {
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
    public Double getRmaCost(String projectName, String phase, ProjectStageCost projectStageCost) {
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
    public Double getObaCost(String projectName, String phase, ProjectStageCost projectStageCost) {
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
    public Double getTravelCost(String projectName, String phase, ProjectStageCost projectStageCost) {
        //TODO... Design阶段差旅费成本接口
        return null;
    }

    /**
     * Design阶段预防成本计算
     * 预防成本 （治具工具成本 + 研发人员薪资成本）
     * @param projectStageCost
     * @return
     */
    @Override
    public Double computePrecautionCost(ProjectStageCost projectStageCost) {
        //TODO... Design阶段预防成本计算
        // 预防成本 （治具工具成本 + 研发人员薪资成本）
        Double toolCost = projectStageCost.getToolCost();
        Double salaryCost = projectStageCost.getSalaryCost();

        return DoubleUtil.sum(toolCost, salaryCost);
    }

    /**
     * Design阶段鉴定成本计算
     * 鉴定成本 (验证费用成本)
     * @param projectStageCost
     * @return
     */
    @Override
    public Double computeIdentifyCost(ProjectStageCost projectStageCost) {
        //TODO... Design阶段鉴定成本计算
        // 鉴定成本 (验证费用成本)
        Double validationCost = projectStageCost.getValidationCost();

        return DoubleUtil.sum(validationCost);
    }

    /**
     * Design阶段内损成本计算
     * 内损成本 (差旅费成本)
     * @param projectStageCost
     * @return
     */
    @Override
    public Double computeInLossCost(ProjectStageCost projectStageCost) {
        //TODO... Design阶段内损成本计算
        // 内损成本 (差旅费成本)
        return projectStageCost.getTravelCost();
    }

    /**
     * Design阶段外损成本计算
     * @param projectStageCost
     * @return
     */
    @Override
    public Double computeOutLossCost(ProjectStageCost projectStageCost) {
        //TODO... Design阶段外损成本计算
        return null;
    }
}
