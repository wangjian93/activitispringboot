package com.ivo.modules.coq.cost.formula;

/**
 * 机种成本计算service
 * @Author: wj
 * @Date: 2019-07-28 15:04
 * @Version 1.0
 */
public interface CostService extends StageCostFormula, ProjectCostFormula {

    /**
     * 获取机种的阶段成本的计算公式
     * @param stage
     * @return
     */
    StageCostFormula getStageCostFormula(String stage);

    /**
     * 获取机种成本汇总的计算公式
     * @return
     */
    ProjectCostFormula getProjectCostFormula();

}
