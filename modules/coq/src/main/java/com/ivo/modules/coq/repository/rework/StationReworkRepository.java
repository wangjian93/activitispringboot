package com.ivo.modules.coq.repository.rework;

import com.ivo.modules.coq.domain.rework.StationRework;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wj
 * @version 1.0
 */
public interface StationReworkRepository extends JpaRepository<StationRework, Long> {

    List<StationRework> findByProject(String project);

}
