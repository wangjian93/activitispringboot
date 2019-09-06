package com.ivo.modules.coq.repository;

import com.ivo.modules.coq.domain.TravelProjectCost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: wj
 * @Date: 2019-09-02 01:53
 * @Version 1.0
 */
public interface TravelProjectCostRepository extends JpaRepository<TravelProjectCost, Long> {

    List<TravelProjectCost> findByProjectAndStage(String project, String stage);

    List<TravelProjectCost> findByProject(String project);
}
