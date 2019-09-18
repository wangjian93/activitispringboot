package com.ivo.modules.coq.repository;

import com.ivo.modules.coq.domain.QmsValidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: wj
 * @Date: 2019-09-09 02:00
 * @Version 1.0
 */
public interface QmsValidateRepository extends JpaRepository<QmsValidate, Long> {

    List<QmsValidate> findByProject(String project);
}
