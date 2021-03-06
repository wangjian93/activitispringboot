package com.ivo.modules.coq.repository.validate;

import com.ivo.modules.coq.domain.validate.ValidateMachine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: wj
 * @Date: 2019-09-08 18:41
 * @Version 1.0
 */
public interface ValidateMachineRepository extends JpaRepository<ValidateMachine, Long> {

    List<ValidateMachine> findByType(String type);
}
