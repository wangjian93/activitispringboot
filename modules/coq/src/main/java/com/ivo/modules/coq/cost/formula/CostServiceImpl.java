package com.ivo.modules.coq.cost.formula;

import com.ivo.modules.coq.enums.PhaseEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wj
 * @Date: 2019-07-28 15:10
 * @Version 1.0
 */
@Service
public class CostServiceImpl implements CostService {

    private static Map<String, StageCostFormula> map;

    private static ProjectCostFormula projectCostFormula;

    static {
        map = new HashMap<>();
        map.put(PhaseEnum.NPRB.getPhase(), new NprbStageCostFormulaImpl());
        map.put(PhaseEnum.Design.getPhase(), new DesignStageCostFormulaImpl());
        map.put(PhaseEnum.EVT.getPhase(), new EvtStageCostFormulaImpl());
        map.put(PhaseEnum.DVT.getPhase(), new DvtStageCostFormulaImpl());
        map.put(PhaseEnum.PVT.getPhase(), new PvtStageCostFormulaImpl());

        projectCostFormula = new ProjectCostFormulaImpl();
    }

    @Override
    public StageCostFormula getStageCostFormula(String stage) {
        // 阶段截取掉"-"及后内容
        stage = StringUtils.substringBefore(stage, "-");
        return map.get(stage);
    }

    @Override
    public ProjectCostFormula getProjectCostFormula() {
        return projectCostFormula;
    }
}
