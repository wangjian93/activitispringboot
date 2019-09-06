package com.ivo.modules.coq.service;

import com.ivo.modules.coq.domain.TravelProjectCost;

import java.util.List;

/**
 * @Author: wj
 * @Date: 2019-09-02 01:56
 * @Version 1.0
 */
public interface TravelProjectCostService {

    List<TravelProjectCost> getTravelProjectCost(String project);

    List<TravelProjectCost> getTravelProjectCost(String project, String stage);

    List<TravelProjectCost> syncTravelProjectCostm(String project);
}
