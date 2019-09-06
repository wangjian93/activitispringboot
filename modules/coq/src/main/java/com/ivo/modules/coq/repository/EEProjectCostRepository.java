package com.ivo.modules.coq.repository;

import com.ivo.modules.coq.domain.EEProjectCost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: wj
 * @Date: 2019-08-23 10:35
 * @Version 1.0
 */
public interface EEProjectCostRepository extends JpaRepository<EEProjectCost, Long> {

    List<EEProjectCost> findByProject(String project);

    EEProjectCost findDistinctByProjectAndStageAndProcess(String project, String stage, String process);
}
