package com.ivo.modules.coq.repository;

import com.ivo.modules.coq.domain.BmCost;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: wj
 * @Date: 2019-08-19 09:07
 * @Version 1.0
 */
public interface BmRepository extends JpaRepository<BmCost, Long> {

    BmCost findDistinctByProjectAndStage(String project, String stage);
}
