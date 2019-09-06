package com.ivo.modules.coq.service;

import com.ivo.modules.coq.domain.ProjectCost2;

/**
 * @Author: wj
 * @Date: 2019-08-30 14:34
 * @Version 1.0
 */
public interface ProjectCostService2 {

    ProjectCost2 getProject(String project);

    ProjectCost2 syncScheduleFromPlm(String project);
}
