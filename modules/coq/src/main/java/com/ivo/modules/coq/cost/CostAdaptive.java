package com.ivo.modules.coq.cost;

import com.ivo.modules.coq.cost.formula.*;
import com.ivo.modules.coq.enums.PhaseEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 适配各阶段成本的获取和计算方式
 * @Author: wj
 * @Date: 2019-06-26 16:18
 * @Version 1.0
 */
public class CostAdaptive {

    private static Map<String, StageCostFormula> map;

    static {
        map = new HashMap<>();
        map.put(PhaseEnum.NPRB.getPhase(), new NprbStageCostFormulaImpl());
        map.put(PhaseEnum.Design.getPhase(), new DesignStageCostFormulaImpl());
        map.put(PhaseEnum.EVT.getPhase(), new EvtStageCostFormulaImpl());
        map.put(PhaseEnum.DVT.getPhase(), new DvtStageCostFormulaImpl());
        map.put(PhaseEnum.PVT.getPhase(), new PvtStageCostFormulaImpl());
    }

    /**
     * 获取阶段的成本数据获取及计算方式
     * @param phase
     * @return
     */
    public static StageCostFormula getCost(String phase) {
        return costAdaptive(phase);
    }

    /**
     * 根据机种阶段进行适配
     * @return
     */
    private static StageCostFormula costAdaptive(String phase) {
        // 阶段截取掉"-"及后内容
        phase = StringUtils.substringBefore(phase, "-");
        return map.get(phase);
    }
}
