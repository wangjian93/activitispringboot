package com.ivo.modules.coq.cost;

import com.ivo.modules.coq.domain.PhaseCostDetail;

import java.util.List;

/**
 * 获取成本数据
 * @Author: wj
 * @Date: 2019-06-26 13:32
 * @Version 1.0
 */
public interface PhaseCostDetailProcess {

    /**
     * 获取机种各个阶段的各种成本数据
     * @param projectName
     * @return
     */
    List<PhaseCostDetail> getPhaseCostDetail(String projectName, List<String> phases);

}
