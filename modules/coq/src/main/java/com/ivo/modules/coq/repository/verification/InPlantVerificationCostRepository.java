package com.ivo.modules.coq.repository.verification;

import com.ivo.modules.coq.domain.verification.InPlantVerificationCost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: wj
 * @Date: 2019-09-19 14:16
 * @Version 1.0
 */
public interface InPlantVerificationCostRepository extends JpaRepository<InPlantVerificationCost, Long> {

    List<InPlantVerificationCost> findByProject(String project);

    List<InPlantVerificationCost> findByProjectAndStage(String project, String stage);
}
