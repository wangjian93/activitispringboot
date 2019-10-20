package com.ivo.modules.coq.repository.rework;

import com.ivo.modules.coq.domain.rework.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: wj
 * @Date: 2019-10-13 23:19
 * @Version 1.0
 */
public interface StationRepository extends JpaRepository<Station, Long> {

    List<Station> findByProject(String project);

}
