package com.ivo.modules.coq.cost.formula;

import com.ivo.modules.coq.domain.ProjectStageCost;

/**
 * 获取、计算机种的阶段成本
 * @Author: wj
 * @Date: 2019-06-24 14:16
 * @Version 1.0
 */
public interface StageCostFormula {

    /**
     * 获取直接材料成本
     * @param projectName 机种
     * @param stage 阶段
     * @param projectStageCost
     * @return
     */
    Double getDirectMaterialCost(String projectName, String stage, ProjectStageCost projectStageCost);

    /**
     * 获取治工具成本
     * @param projectName 机种
     * @param stage 阶段
     * @param projectStageCost
     * @return
     */
    Double getToolCost(String projectName, String stage, ProjectStageCost projectStageCost);

    /**
     * 获取验证费用
     * @param projectName 机种
     * @param stage 阶段
     * @param projectStageCost
     * @return
     */
    Double getValidationCost(String projectName, String stage, ProjectStageCost projectStageCost);

    /**
     * 获取生产费用
     * @param projectName 机种
     * @param stage 阶段
     * @param projectStageCost
     * @return
     */
    Double getProductionCost(String projectName, String stage, ProjectStageCost projectStageCost);

    /**
     * 获取重工/报废费用
     * @param projectName 机种
     * @param stage 阶段
     * @param projectStageCost
     * @return
     */
    Double getReworkAndScrapCost(String projectName, String stage, ProjectStageCost projectStageCost);

    /**
     * 获取研发人员薪资
     * @param projectName 机种
     * @param stage 阶段
     * @param projectStageCost
     * @return
     */
    Double getSalaryCost(String projectName, String stage, ProjectStageCost projectStageCost);


    /**
     * 获取RMA成本
     * @param projectName 机种
     * @param phase 阶段
     * @param projectStageCost
     * @return
     */
    Double getRmaCost(String projectName, String phase, ProjectStageCost projectStageCost);

    /**
     * 获取OBA成本
     * @param projectName
     * @param projectStageCost
     * @return
     */
    Double getObaCost(String projectName, String phase, ProjectStageCost projectStageCost);

    /**
     * 获取差旅费用
     * @param projectName 机种
     * @param phase 阶段
     * @param projectStageCost
     * @return
     */
    Double getTravelCost(String projectName, String phase, ProjectStageCost projectStageCost);

    /**
     * 计算预防成本
     * @param projectStageCost
     * @return
     */
    Double computePrecautionCost(ProjectStageCost projectStageCost);

    /**
     * 计算鉴定成本
     * @param projectStageCost
     * @return
     */
    Double computeIdentifyCost(ProjectStageCost projectStageCost);


    /**
     * 计算内损成本
     * @param projectStageCost
     * @return
     */
    Double computeInLossCost(ProjectStageCost projectStageCost);

    /**
     * 计算外损成本
     * @param projectStageCost
     * @return
     */
    Double computeOutLossCost(ProjectStageCost projectStageCost);
}
