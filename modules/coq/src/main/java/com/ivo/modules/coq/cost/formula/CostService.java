package com.ivo.modules.coq.cost.formula;

import org.springframework.stereotype.Service;

/**
 * @Author: wj
 * @Date: 2019-07-28 15:04
 * @Version 1.0
 */
public interface CostService {

    /**
     * 获取阶段的成本获取、计算公式
     * @param stage
     * @return
     */
    StageCostFormula getStageCostFormula(String stage);


    ProjectCostFormula getProjectCostFormula();

}
