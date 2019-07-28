package com.ivo.modules.coq.service;

import com.ivo.modules.coq.domain.ProjectCost;


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
     * 生成机种的成本数据
     * @param projectName
     * @return
     */
    ProjectCost generateProjectCost(String projectName);

    /**
     * 更新机种的成本数据
     * @param projectName
     * @return
     */
    ProjectCost updateProjectCost(String projectName);

    /**
     * 持久化机种成本数据
     * @param projectCost
     */
    void saveProjectCost(ProjectCost projectCost);

    /**
     * 生成或更新机种的成本数据
     * @param projectCost
     * @return
     */
    void generateOrUpdateProjectCost(ProjectCost projectCost);

    /**
     * 生成机种的阶段信息
     * @param projectCost
     */
    void setStage(ProjectCost projectCost);

    /**
     * 生成机种阶段成本信息
     * @param projectCost
     */
    void setStageCost(ProjectCost projectCost);

    /**
     * 生成机种的成本信息，成本的汇总
     * @param projectCost
     */
    void setProjectCost(ProjectCost projectCost);
}
