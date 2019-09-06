package com.ivo.modules.coq.cost.formula;

import com.ivo.modules.coq.cost.DoubleUtil;
import com.ivo.modules.coq.domain.ProjectCost;
import com.ivo.modules.coq.domain.ProjectStageCost;
import org.springframework.stereotype.Service;

/**
 * 计算机种的成本，汇总成本
 * @Author: wj
 * @Date: 2019-07-28 15:01
 * @Version 1.0
 */
@Service(value = "projectCostFormula")
public class ProjectCostFormulaImpl implements ProjectCostFormula {

    /**
     * 汇总必要花费
     * @param projectCost
     * @return
     */
    @Override
    public Double summaryNecessaryCost(ProjectCost projectCost) {
        return DoubleUtil.sum(projectCost.getPrecautionCost(), projectCost.getIdentifyCost());
    }

    /**
     * 汇总多余花费
     * @param projectCost
     * @return
     */
    @Override
    public Double summaryUnnecessaryCost(ProjectCost projectCost) {
        return DoubleUtil.sum(projectCost.getInLossCost(), projectCost.getOutLossCost());
    }

    /**
     * 汇总预防成本
     * @param projectCost
     * @return
     */
    @Override
    public Double summaryPrecautionCost(ProjectCost projectCost) {
        Double d = null;
        for(ProjectStageCost projectStageCost : projectCost.getProjectStageCosts()) {
            d = DoubleUtil.sum(d, projectStageCost.getPrecautionCost());
        }
        return d;
    }

    /**
     * 汇总鉴定成本
     * @param projectCost
     * @return
     */
    @Override
    public Double summaryIdentifyCost(ProjectCost projectCost) {
        Double d = null;
        for(ProjectStageCost projectStageCost : projectCost.getProjectStageCosts()) {
            d = DoubleUtil.sum(d, projectStageCost.getIdentifyCost());
        }
        return d;
    }

    /**
     * 汇总内损成本
     * @param projectCost
     * @return
     */
    @Override
    public Double summaryInLossCost(ProjectCost projectCost) {
        Double d = null;
        for(ProjectStageCost projectStageCost : projectCost.getProjectStageCosts()) {
            d = DoubleUtil.sum(d, projectStageCost.getInLossCost());
        }
        return d;
    }

    /**
     * 汇总外损成本
     * @param projectCost
     * @return
     */
    @Override
    public Double summaryOutLossCost(ProjectCost projectCost) {
        Double d = null;
        for(ProjectStageCost projectStageCost : projectCost.getProjectStageCosts()) {
            d = DoubleUtil.sum(d, projectStageCost.getOutLossCost());
        }
        return d;
    }
}
