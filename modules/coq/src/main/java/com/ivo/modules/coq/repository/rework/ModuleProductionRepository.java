package com.ivo.modules.coq.repository.rework;

import com.ivo.modules.coq.domain.rework.ModuleProduction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wj
 * @version 1.0
 */
public interface ModuleProductionRepository extends JpaRepository<ModuleProduction, Long> {

    List<ModuleProduction> findByProject(String project);
}
