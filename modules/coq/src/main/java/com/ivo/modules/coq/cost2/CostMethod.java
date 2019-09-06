package com.ivo.modules.coq.cost2;

import com.ivo.modules.coq.domain.ProjectCost2;

/**
 * 机种成本计算方法
 * @Author: wj
 * @Date: 2019-08-30 14:21
 * @Version 1.0
 */
public interface CostMethod {

    /**
     * 必要花费
     * @return
     */
    void getNecessaryCost(ProjectCost2 projectCost);

    /**
     * 多余花费
     * @return
     */
    void getUnnecessaryCost(ProjectCost2 projectCost);

    /**
     * 预防成本
     * @return
     */
    void getPrecautionCost(ProjectCost2 projectCost);

    /**
     * 鉴定成本
     * @return
     */
    void getIdentifyCost(ProjectCost2 projectCost);

    /**
     * 内损成本
     * @return
     */
    void getInLossCost(ProjectCost2 projectCost);

    /**
     * 外损成本
     * @return
     */
    void getOutLossCost(ProjectCost2 projectCost);
}
