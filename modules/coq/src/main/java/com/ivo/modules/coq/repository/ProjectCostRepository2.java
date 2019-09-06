package com.ivo.modules.coq.repository;

import com.ivo.modules.coq.domain.ProjectCost2;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: wj
 * @Date: 2019-08-30 14:36
 * @Version 1.0
 */
public interface ProjectCostRepository2 extends JpaRepository<ProjectCost2, Long> {

    ProjectCost2 findDistinctByProject(String project);
}
