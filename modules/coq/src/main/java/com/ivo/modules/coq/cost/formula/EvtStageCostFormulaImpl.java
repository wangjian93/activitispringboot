package com.ivo.modules.coq.cost.formula;

import com.ivo.modules.coq.cost.DoubleUtil;
import com.ivo.modules.coq.domain.ProjectStageCost;
import org.springframework.stereotype.Service;

/**
 * 获取EVT阶段的成本数据
 * @Author: wj
 * @Date: 2019-06-24 14:43
 * @Version 1.0
 */
@Service(value = "evtStageCostFormula")
public class EvtStageCostFormulaImpl extends AbstractStageCostFormula {

    /**
     * EVT阶段的预防成本
     * 直接材料 + 治工具 + 生产费用
     * @param projectStageCost
     * @return
     */
    @Override
    public Double computePrecautionCost(ProjectStageCost projectStageCost) {
        return DoubleUtil.sum(projectStageCost.getDirectMaterialCost(),
                projectStageCost.getToolCost(),
                projectStageCost.getProductionCost());
    }

    /**
     * EVT阶段的鉴定成本
     * 验证费用
     * @param projectStageCost
     * @return
     */
    @Override
    public Double computeIdentifyCost(ProjectStageCost projectStageCost) {
        return projectStageCost.getValidationCost();
    }

    /**
     * EVT阶段的内损成本
     * 直接材料成本 + 治工具费用 + 生产费用 + 重工报废费用 + 验证费用
     * @param projectStageCost
     * @return
     */
    @Override
    public Double computeInLossCost(ProjectStageCost projectStageCost) {
        return DoubleUtil.sum(projectStageCost.getDirectMaterialCost(),
                projectStageCost.getToolCost(),
                projectStageCost.getProductionCost(),
                projectStageCost.getValidationCost());
    }
}
