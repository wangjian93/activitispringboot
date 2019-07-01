package com.ivo.modules.coq.cost;

import com.ivo.modules.coq.cost.formula.Cost;
import com.ivo.modules.coq.domain.PhaseCostDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wj
 * @Date: 2019-06-26 16:28
 * @Version 1.0
 */
public class PhaseCostDetailProcessImpl implements PhaseCostDetailProcess  {

    @Override
    public List<PhaseCostDetail> getPhaseCostDetail(String projectName, List<String> phases) {
        List<PhaseCostDetail> phaseCostDetails = new ArrayList<>();

        for(String phase : phases) {

            Cost cost = CostAdaptive.getCost(phase);

            PhaseCostDetail phaseCostDetail = new PhaseCostDetail(phase);
            phaseCostDetail.setDirectMaterialCost(cost.getDirectMaterialCost(projectName, phase));
            phaseCostDetail.setToolCost(cost.getToolCost(projectName, phase));
            phaseCostDetail.setValidationCost(cost.getValidationCost(projectName, phase));
            phaseCostDetail.setProductionCost(cost.getProductionCost(projectName, phase));
            phaseCostDetail.setReworkAndScrapCost(cost.getReworkAndScrapCost(projectName, phase));
            phaseCostDetail.setSalaryCost(cost.getSalaryCost(projectName, phase));
            phaseCostDetail.setRmaCost(cost.getRmaCost(projectName, phase));
            phaseCostDetail.setObaCost(cost.getObaCost(projectName, phase));
            phaseCostDetail.setTravelCost(cost.getTravelCost(projectName, phase));

            phaseCostDetails.add(phaseCostDetail);
        }

        return phaseCostDetails;
    }
}
