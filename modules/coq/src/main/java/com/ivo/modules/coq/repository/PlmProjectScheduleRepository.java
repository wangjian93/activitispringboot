package com.ivo.modules.coq.repository;

import com.ivo.modules.coq.domain.PlmProjectSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: wj
 * @Date: 2019-08-20 16:14
 * @Version 1.0
 */
public interface PlmProjectScheduleRepository extends JpaRepository<PlmProjectSchedule, Long> {

    List<PlmProjectSchedule> findByProject(String project);

    PlmProjectSchedule findDistinctByProjectAndStage(String project, String stage);
}
