package com.ivo.modules.coq.cost2.impl;

import com.ivo.modules.coq.cost.DoubleUtil;
import com.ivo.modules.coq.cost2.CostMethod;
import com.ivo.modules.coq.domain.ProjectCost2;
import com.ivo.modules.coq.domain.ProjectSecondarySubjectCost2;
import org.springframework.stereotype.Service;

/**
 * @Author: wj
 * @Date: 2019-08-30 14:30
 * @Version 1.0
 */
@Service
public class CostMethodImpl implements CostMethod {

    @Override
    public void getNecessaryCost(ProjectCost2 projectCost) {
        Double necessaryCost = DoubleUtil.sum(projectCost.getPrecautionCost(), projectCost.getIdentifyCost());
        projectCost.setNecessaryCost(necessaryCost);
    }

    @Override
    public void getUnnecessaryCost(ProjectCost2 projectCost) {
        Double unNecessaryCost = DoubleUtil.sum(projectCost.getInLossCost(), projectCost.getOutLossCost());
        projectCost.setUnnecessaryCost(unNecessaryCost);
    }

    @Override
    public void getPrecautionCost(ProjectCost2 projectCost) {
        Double precautionCost = null;
        for(ProjectSecondarySubjectCost2 projectSecondarySubjectCost : projectCost.getProjectSecondarySubjectCostList()) {
            precautionCost = DoubleUtil.sum(precautionCost, projectSecondarySubjectCost.getPrecautionCost());
        }
        projectCost.setPrecautionCost(precautionCost);
    }

    @Override
    public void getIdentifyCost(ProjectCost2 projectCost) {
        Double identifyCost = null;
        for(ProjectSecondarySubjectCost2 projectSecondarySubjectCost : projectCost.getProjectSecondarySubjectCostList()) {
            identifyCost = DoubleUtil.sum(identifyCost, projectSecondarySubjectCost.getIdentifyCost());
        }
        projectCost.setIdentifyCost(identifyCost);
    }

    @Override
    public void getInLossCost(ProjectCost2 projectCost) {
        Double inLossCost = null;
        for(ProjectSecondarySubjectCost2 projectSecondarySubjectCost : projectCost.getProjectSecondarySubjectCostList()) {
            inLossCost = DoubleUtil.sum(inLossCost, projectSecondarySubjectCost.getInLossCost());
        }
        projectCost.setInLossCost(inLossCost);
    }

    @Override
    public void getOutLossCost(ProjectCost2 projectCost) {
        Double outLossCost = null;
        for(ProjectSecondarySubjectCost2 projectSecondarySubjectCost : projectCost.getProjectSecondarySubjectCostList()) {
            outLossCost = DoubleUtil.sum(outLossCost, projectSecondarySubjectCost.getOutLossCost());
        }
        projectCost.setOutLossCost(outLossCost);
    }
}
