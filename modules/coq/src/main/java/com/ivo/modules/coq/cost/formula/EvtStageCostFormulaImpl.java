package com.ivo.modules.coq.cost.formula;

import com.ivo.modules.coq.domain.ProjectStageCost;

/**
 * 获取EVT阶段的成本数据
 * @Author: wj
 * @Date: 2019-06-24 14:43
 * @Version 1.0
 */
public class EvtStageCostFormulaImpl implements StageCostFormula {



    /**
     * EVT阶段直接材料成本接口
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    @Override
    public Double getDirectMaterialCost(String projectName, String phase, ProjectStageCost projectStageCost) {
        //TODO... EVT阶段直接材料成本接口
        return null;
    }

    /**
     * EVT阶段治工具成本接口
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    @Override
    public Double getToolCost(String projectName, String phase,  ProjectStageCost projectStageCost) {
        //TODO... EVT阶段治工具成本接口
        return null;
    }

    /**
     * EVT阶段验证费用成本接口
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    @Override
    public Double getValidationCost(String projectName, String phase, ProjectStageCost projectStageCost) {
        //TODO... EVT阶段验证费用成本接口
        return null;
    }

    /**
     * EVT阶段生产费用成本接口
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    @Override
    public Double getProductionCost(String projectName, String phase,  ProjectStageCost ProjectStageCost) {
        //TODO... EVT阶段生产费用成本接口
        return null;
    }

    /**
     * EVT阶段重工报废费用成本接口
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    @Override
    public Double getReworkAndScrapCost(String projectName, String phase,  ProjectStageCost projectStageCost) {
        //TODO... EVT阶段重工报废费用成本接口
        return null;
    }

    /**
     * EVT阶段研发人员薪资成本接口
     * @param projectName
     * @param phase
     * @return
     */
    @Override
    public Double getSalaryCost(String projectName, String phase,  ProjectStageCost projectStageCost) {
        //TODO... EVT阶段研发人员薪资成本接口
        return null;
    }

    /**
     * EVT阶段RMA成本接口
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    @Override
    public Double getRmaCost(String projectName, String phase,  ProjectStageCost projectStageCost) {
        //TODO... EVT阶段RMA成本接口
        return null;
    }

    /**
     * EVT阶段OBA成本接口
     * @param projectName
     * @param phase
     * @return
     */
    @Override
    public Double getObaCost(String projectName, String phase, ProjectStageCost projectStageCost) {
        //TODO... EVT阶段OBA成本接口
        return null;
    }

    /**
     * EVT阶段差旅费成本接口
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    @Override
    public Double getTravelCost(String projectName, String phase,  ProjectStageCost projectStageCost) {
        //TODO... EVT阶段差旅费成本接口
        return null;
    }

    /**
     * EVT阶段预防成本计算
     * @param projectStageCost
     * @return
     */
    @Override
    public Double computePrecautionCost(ProjectStageCost projectStageCost) {
        //TODO... EVT阶段预防成本计算
        return null;
    }

    /**
     * EVT阶段鉴定成本计算
     * @param projectStageCost
     * @return
     */
    @Override
    public Double computeIdentifyCost(ProjectStageCost projectStageCost) {
        //TODO... EVT阶段鉴定成本计算
        return null;
    }

    /**
     * EVT阶段内损成本计算
     * @param projectStageCost
     * @return
     */
    @Override
    public Double computeInLossCost(ProjectStageCost projectStageCost) {
        //TODO... EVT阶段内损成本计算
        return null;
    }

    /**
     * EVT阶段外损成本计算
     * @param projectStageCost
     * @return
     */
    @Override
    public Double computeOutLossCost(ProjectStageCost projectStageCost) {
        //TODO... EVT阶段外损成本计算
        return null;
    }
}
