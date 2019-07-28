package com.ivo.modules.coq.cost.formula;

import com.ivo.modules.coq.cost.DoubleUtil;
import com.ivo.modules.coq.domain.ProjectStageCost;

/**
 * 获取Pvt阶段的成本数据
 * @Author: wj
 * @Date: 2019-06-24 14:46
 * @Version 1.0
 */
public class PvtStageCostFormulaImpl implements StageCostFormula {



    /**
     * PVT阶段直接材料成本接口
     * @param projectName 机种
     * @param stage 阶段
     * @return
     */
    @Override
    public Double getDirectMaterialCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        //TODO... PVT阶段直接材料成本接口
        return 78156.00;
    }

    /**
     * PVT阶段治工具成本接口
     * @param projectName 机种
     * @param stage 阶段
     * @return
     */
    @Override
    public Double getToolCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        //TODO... PVT阶段治工具成本接口
        return null;
    }

    /**
     * PVT阶段验证费用成本接口
     * @param projectName 机种
     * @param stage 阶段
     * @return
     */
    @Override
    public Double getValidationCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        //TODO... PVT阶段验证费用成本接口
        return null;
    }

    /**
     * PVT阶段生产费用成本接口
     * @param projectName 机种
     * @param stage 阶段
     * @return
     */
    @Override
    public Double getProductionCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        //TODO... PVT阶段生产费用成本接口
        return null;
    }

    /**
     * PVT阶段重工报废费用成本接口
     * @param projectName 机种
     * @param stage 阶段
     * @return
     */
    @Override
    public Double getReworkAndScrapCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        //TODO... PVT阶段重工报废费用成本接口
        return 52782.03;
    }

    /**
     * PVT阶段研发人员薪资成本接口
     * @param projectName
     * @param stage
     * @return
     */
    @Override
    public Double getSalaryCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        //TODO... PVT阶段研发人员薪资成本接口
        return null;
    }

    /**
     * PVT阶段RMA成本接口
     * @param projectName 机种
     * @param stage 阶段
     * @return
     */
    @Override
    public Double getRmaCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        //TODO... PVT阶段RMA成本接口
        return 0D;
    }

    /**
     * PVT阶段OBA成本接口
     * @param projectName
     * @param stage
     * @return
     */
    @Override
    public Double getObaCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        //TODO... PVT阶段OBA成本接口
        return 0D;
    }

    /**
     * PVT阶段差率费成本接口
     * @param projectName 机种
     * @param stage 阶段
     * @return
     */
    @Override
    public Double getTravelCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        //TODO... PVT阶段差率费成本接口
        return 0D;
    }

    /**
     * PVT阶段预防成本计算
     * @param projectStageCost
     * @return
     */
    @Override
    public Double computePrecautionCost(ProjectStageCost projectStageCost) {
        //TODO... PVT阶段预防成本计算
        return null;
    }

    /**
     * PVT阶段鉴定成本计算
     * @param projectStageCost
     * @return
     */
    @Override
    public Double computeIdentifyCost(ProjectStageCost projectStageCost) {
        //TODO... PVT阶段鉴定成本计算
        return null;
    }

    /**
     * PVT阶段内损成本计算
     * 内损成本 （直接材料成本 + 重工报废费用）
     * @param projectStageCost
     * @return
     */
    @Override
    public Double computeInLossCost(ProjectStageCost projectStageCost) {
        //TODO... PVT阶段内损成本计算
        // 内损成本 （直接材料成本 + 重工报废费用）
        Double directMaterialCost = projectStageCost.getDirectMaterialCost();
        Double reworkAndScrapCost = projectStageCost.getReworkAndScrapCost();
        return DoubleUtil.sum(directMaterialCost, reworkAndScrapCost);
    }

    /**
     * PVT阶段外损成本计算
     * 外损成本 （RMA + OBA）
     * @param projectStageCost
     * @return
     */
    @Override
    public Double computeOutLossCost(ProjectStageCost projectStageCost) {
        //TODO... PVT阶段外损成本计算
        // 外损成本 （RMA + OBA）
        Double rmaCost = projectStageCost.getRmaCost();
        Double obaCost = projectStageCost.getObaCost();
        return DoubleUtil.sum(rmaCost, obaCost);
    }
}
