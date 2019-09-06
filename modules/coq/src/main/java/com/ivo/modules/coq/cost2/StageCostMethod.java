package com.ivo.modules.coq.cost2;

import com.ivo.modules.coq.domain.ProjectStageCost2;

/**
 * 机种阶段成本计算方式
 * @Author: wj
 * @Date: 2019-08-30 14:57
 * @Version 1.0
 */
public interface StageCostMethod {
    /**
     * 直接材料成本
     */
    void getDirectMaterialCost(ProjectStageCost2 projectStageCost);

    /**
     * 治工具成本
     */
    void getToolCost(ProjectStageCost2 projectStageCost);

    /**
     * 验证费用
     */
    void getValidationCost(ProjectStageCost2 projectStageCost);

    /**
     * 生产费用
     */
    void getProductionCost(ProjectStageCost2 projectStageCost);

    /**
     * 重工/报废费用
     */
    void getReworkAndScrapCost(ProjectStageCost2 projectStageCost);

    /**
     * 研发人员薪资费用
     */
    void getSalaryCost(ProjectStageCost2 projectStageCost);

    /**
     * RMA成本
     */
    void getRmaCost(ProjectStageCost2 projectStageCost);

    /**
     * OBA成本
     */
    void getObaCost(ProjectStageCost2 projectStageCost);

    /**
     * 差旅费用
     */
    void getTravelCost(ProjectStageCost2 projectStageCost);
}
