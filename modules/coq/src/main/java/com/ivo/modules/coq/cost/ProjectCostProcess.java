package com.ivo.modules.coq.cost;

import com.ivo.modules.coq.domain.ProjectCost;

/**
 * 处理ProjectCost的数据
 * @Author: wj
 * @Date: 2019-06-24 15:00
 * @Version 1.0
 */
public interface ProjectCostProcess extends PhaseCostProcess, PhaseCostDetailProcess  {

    /**
     * 计算机种的成本（预防成本，鉴定成本，内损成本，外损成本，必要花费，多余花费）
     * @param projectCost
     * @return
     */
    void computeProjectCost(ProjectCost projectCost);

}
