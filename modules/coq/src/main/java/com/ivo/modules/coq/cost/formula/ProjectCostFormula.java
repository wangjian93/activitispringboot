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
     * 汇总必要花费
     * @param projectCost
     * @return
     */
    Double summaryNecessaryCost(ProjectCost projectCost);

    /**
     * 汇总多余花费
     * @param projectCost
     * @return
     */
    Double summaryUnnecessaryCost(ProjectCost projectCost);

    /**
     * 汇总预防成本
     * @param projectCost
     * @return
     */
    Double summaryPrecautionCost(ProjectCost projectCost);

    /**
     * 汇总鉴定成本
     * @param projectCost
     * @return
     */
    Double summaryIdentifyCost(ProjectCost projectCost);

    /**
     * 汇总内损成本
     * @param projectCost
     * @return
     */
    Double summaryInLossCost(ProjectCost projectCost);

    /**
     * 汇总外损成本
     * @param projectCost
     * @return
     */
    Double summaryOutLossCost(ProjectCost projectCost);
}
