package com.ivo.modules.coq.service;

import com.ivo.modules.coq.domain.EEProjectCost;

import java.util.List;

/**
 * EE/ED BOM 工单 service
 * @Author: wj
 * @Date: 2019-08-23 10:38
 * @Version 1.0
 */
public interface EEProjectCostService {

    /**
     *
     * @param project
     * @return
     */
    List<EEProjectCost> getEEProjectCost(String project);

    /**
     * 从ee/de bom 工单同步数据
     * @param project
     * @return
     */
    List<EEProjectCost> syncFromEeBomWo(String project);

    EEProjectCost getEEProject(String project, String stage, String process);
}
