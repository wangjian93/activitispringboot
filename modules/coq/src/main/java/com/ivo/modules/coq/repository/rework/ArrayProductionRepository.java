package com.ivo.modules.coq.repository.rework;

import com.ivo.modules.coq.domain.rework.ArrayProduction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wj
 * @version 1.0
 */
public interface ArrayProductionRepository extends JpaRepository<ArrayProduction, Long> {

    List<ArrayProduction> findByProject(String project);
}
