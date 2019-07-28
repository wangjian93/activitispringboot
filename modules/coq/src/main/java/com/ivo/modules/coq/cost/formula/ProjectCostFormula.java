package com.ivo.modules.coq.cost.formula;

import com.ivo.modules.coq.domain.ProjectCost;

/**
 * 计算机种的成本，汇总成本
 * @Author: wj
 * @Date: 2019-07-28 14:50
 * @Version 1.0
 */
public interface ProjectCostFormula {

    /**
     * 计算必要花费
     * @param projectCost
     * @return
     */
    Double computeNecessaryCost(ProjectCost projectCost);

    /**
     * 计算多余花费
     * @param projectCost
     * @return
     */
    Double computeUnnecessaryCost(ProjectCost projectCost);

    /**
     * 计算预防成本
     * @param projectCost
     * @return
     */
    Double computePrecautionCost(ProjectCost projectCost);

    /**
     * 计算鉴定成本
     * @param projectCost
     * @return
     */
    Double computeIdentifyCost(ProjectCost projectCost);

    /**
     * 计算内损成本
     * @param projectCost
     * @return
     */
    Double computeInLossCost(ProjectCost projectCost);

    /**
     * 计算外损成本
     * @param projectCost
     * @return
     */
    Double computeOutLossCost(ProjectCost projectCost);
}
