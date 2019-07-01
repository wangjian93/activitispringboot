package com.ivo.modules.coq.cost;

import com.ivo.modules.coq.domain.PhaseCost;
import com.ivo.modules.coq.domain.PhaseCostDetail;

import java.util.List;

/**
 * 处理PhaseCost
 * @Author: wj
 * @Date: 2019-06-24 15:24
 * @Version 1.0
 */
public interface PhaseCostProcess extends PhaseCostDetailProcess {

    /**
     * 根据新产品机种的阶段成本明细数据计算出各阶段的费用二级科目
     * @param list
     * @return
     */
    List<PhaseCost> computePhaseCost(List<PhaseCostDetail> list);

}
