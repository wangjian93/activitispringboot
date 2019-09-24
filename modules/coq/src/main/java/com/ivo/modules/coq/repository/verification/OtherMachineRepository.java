package com.ivo.modules.coq.repository.verification;

import com.ivo.modules.coq.domain.verification.OtherMachine;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: wj
 * @Date: 2019-09-19 14:17
 * @Version 1.0
 */
public interface OtherMachineRepository extends JpaRepository<OtherMachine, Long> {

    OtherMachine findFirstByMachineName(String machineName);
}
