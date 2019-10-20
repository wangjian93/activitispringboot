package com.ivo.modules.coq.repository.rework;

import com.ivo.modules.coq.domain.rework.CellProduction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wj
 * @version 1.0
 */
public interface CellProductionRepository extends JpaRepository<CellProduction, Long> {

    List<CellProduction> findByProject(String project);
}
