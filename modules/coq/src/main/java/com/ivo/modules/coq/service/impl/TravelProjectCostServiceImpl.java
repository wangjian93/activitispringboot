package com.ivo.modules.coq.service.impl;

import com.ivo.modules.coq.domain.TravelProjectCost;
import com.ivo.modules.coq.repository.TravelProjectCostRepository;
import com.ivo.modules.coq.service.TravelProjectCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wj
 * @Date: 2019-09-02 01:57
 * @Version 1.0
 */
@Service
public class TravelProjectCostServiceImpl implements TravelProjectCostService {

    @Autowired
    private TravelProjectCostRepository repository;

    @Override
    public List<TravelProjectCost> getTravelProjectCost(String project) {
        return repository.findByProject(project);
    }

    @Override
    public List<TravelProjectCost> getTravelProjectCost(String project, String stage) {
        return repository.findByProjectAndStage(project, stage);
    }

    @Override
    public List<TravelProjectCost> syncTravelProjectCostm(String project) {
        return null;
    }

    private void saveTravelProjectCost(List<TravelProjectCost> list) {
        repository.saveAll(list);
    }

    private void deleteTravelProjectCost(List<TravelProjectCost> list) {
        repository.deleteAll(list);
    }
}
