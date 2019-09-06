package com.ivo.modules.coq.repository;

import com.ivo.modules.coq.domain.BmProjectCost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: wj
 * @Date: 2019-08-22 10:22
 * @Version 1.0
 */
public interface BmProjectCostRepository extends JpaRepository<BmProjectCost, Long> {

    List<BmProjectCost> findByProjectAndStageAndCostTypeAndBudgetType(String project, String stage, String costType, String budgetType);

    List<BmProjectCost> findByProjectAndStageAndCostType(String project, String stage, String costType);

    List<BmProjectCost> findByProjectAndStage(String project, String stage);

    List<BmProjectCost> findByProjectAndCostType(String project, String costType);

    List<BmProjectCost> findByProject(String project);
}
