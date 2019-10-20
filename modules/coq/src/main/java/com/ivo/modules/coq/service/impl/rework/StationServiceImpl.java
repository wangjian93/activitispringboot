package com.ivo.modules.coq.service.impl.rework;

import com.ivo.modules.coq.domain.rework.Station;
import com.ivo.modules.coq.domain.rework.StationRework;
import com.ivo.modules.coq.repository.rework.StationRepository;
import com.ivo.modules.coq.repository.rework.StationReworkRepository;
import com.ivo.modules.coq.service.rework.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wj
 * @Date: 2019-10-13 23:33
 * @Version 1.0
 */
@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationRepository repository;

    @Autowired
    private StationReworkRepository reworkRepository;

    @Override
    public List<Station> getStations(String project) {
        return repository.findByProject(project);
    }

    @Override
    public void saveStations(List<Station> stationList) {
        repository.saveAll(stationList);
    }

    @Override
    public void deleteStations(List<Station> stationList) {
        repository.deleteAll(stationList);
    }


    @Override
    public List<StationRework> getStationReworks(String project) {
        return reworkRepository.findByProject(project);
    }

    @Override
    public void saveStationReworks(List<StationRework> stationReworkList) {
        reworkRepository.saveAll(stationReworkList);
    }

    @Override
    public void deleteStationReworks(List<StationRework> stationReworkList) {
        reworkRepository.deleteAll(stationReworkList);
    }
}
