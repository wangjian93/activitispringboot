package com.ivo.modules.coq.repository;

import com.ivo.modules.coq.domain.PlmProjectStage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: wj
 * @Date: 2019-08-20 14:34
 * @Version 1.0
 */
public interface PlmProjectStageRepository extends JpaRepository<PlmProjectStage, Long> {

    List<PlmProjectStage> findByProject(String project);

    List<PlmProjectStage> findByProjectAndStage(String project, String stage);

    PlmProjectStage findDistinctByProjectAndStageAndProcess(String project, String stage, String process);
}
