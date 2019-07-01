package com.ivo.modules.coq.service;

import com.ivo.modules.coq.domain.ProjectCost;

import java.util.List;

/**
 * @Author: wj
 * @Date: 2019-06-24 15:02
 * @Version 1.0
 */
public interface ProjectCostService {

    /**
     * 获取机种的成本数据
     * @param projectName
     * @return
     */
    ProjectCost getProjectCost(String projectName);

    /**
     * 获取机种的阶段
     * @param projectName
     * @return
     */
    List<String> getPhase(String projectName);

}
