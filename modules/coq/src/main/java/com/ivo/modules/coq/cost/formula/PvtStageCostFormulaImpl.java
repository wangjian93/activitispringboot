package com.ivo.modules.coq.cost.formula;

import com.ivo.modules.coq.cost.DoubleUtil;
import com.ivo.modules.coq.domain.ProjectStageCost;
import org.springframework.stereotype.Service;

/**
 * 获取Pvt阶段的成本数据
 * @Author: wj
 * @Date: 2019-06-24 14:46
 * @Version 1.0
 */
@Service(value = "pvtStageCostFormula")
public class PvtStageCostFormulaImpl extends AbstractStageCostFormula {

    /**
     * PVT阶段的预防成本
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
     * PVT阶段的内损成本
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

    /**
     * PVT阶段的外损成本
     * OBA费用 + RMA费用
     * @param projectStageCost
     * @return
     */
    @Override
    public Double computeOutLossCost(ProjectStageCost projectStageCost) {
        return DoubleUtil.sum(projectStageCost.getObaCost(), projectStageCost.getRmaCost());
    }
}
