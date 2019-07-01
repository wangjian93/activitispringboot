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

    private static Map<String, Cost> map;

    static {
        map = new HashMap<>();
        map.put(PhaseEnum.NPRB.getPhase(), new NprbCost());
        map.put(PhaseEnum.Design.getPhase(), new DesignCost());
        map.put(PhaseEnum.EVT.getPhase(), new EvtCost());
        map.put(PhaseEnum.DVT.getPhase(), new DvtCost());
        map.put(PhaseEnum.PVT.getPhase(), new PvtCost());
    }

    /**
     * 获取阶段的成本数据获取及计算方式
     * @param phase
     * @return
     */
    public static Cost getCost(String phase) {
        return costAdaptive(phase);
    }

    /**
     * 根据机种阶段进行适配
     * @return
     */
    private static Cost costAdaptive(String phase) {
        // 阶段截取掉"-"及后内容
        phase = StringUtils.substringBefore(phase, "-");
        return map.get(phase);
    }
}
