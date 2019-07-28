package com.ivo.modules.coq.repository;

import com.ivo.modules.coq.domain.ProjectCost;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: wj
 * @Date: 2019-07-25 10:18
 * @Version 1.0
 */
public interface ProjectCostRepository extends JpaRepository<ProjectCost, String> {

    ProjectCost findDistinctByProjectName(String projectName);

}
