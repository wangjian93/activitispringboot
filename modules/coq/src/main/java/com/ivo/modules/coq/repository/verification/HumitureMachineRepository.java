package com.ivo.modules.coq.repository.verification;

import com.ivo.modules.coq.domain.verification.HumitureMachine;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: wj
 * @Date: 2019-09-19 14:16
 * @Version 1.0
 */
public interface HumitureMachineRepository extends JpaRepository<HumitureMachine, Long> {

    HumitureMachine findFirstByMachineName(String machineName);

}
