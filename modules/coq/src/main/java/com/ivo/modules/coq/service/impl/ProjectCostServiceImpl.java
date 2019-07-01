package com.ivo.modules.coq.service.impl;

import com.ivo.modules.coq.cost.ProjectCostProcess;
import com.ivo.modules.coq.domain.PhaseCost;
import com.ivo.modules.coq.domain.PhaseCostDetail;
import com.ivo.modules.coq.domain.ProjectCost;
import com.ivo.modules.coq.enums.PhaseEnum;
import com.ivo.modules.coq.service.ProjectCostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wj
 * @Date: 2019-06-24 15:03
 * @Version 1.0
 */
@Service
@Slf4j
public class ProjectCostServiceImpl implements ProjectCostService {

    @Autowired
    private ProjectCostProcess projectCostProcess;

    @Override
    public ProjectCost getProjectCost(String projectName) {
        List<String> phaseList = getPhase(projectName);

        List<PhaseCostDetail> phaseCostDetails = projectCostProcess.getPhaseCostDetail(projectName, phaseList);
        List<PhaseCost> phaseCosts = projectCostProcess.computePhaseCost(phaseCostDetails);

        ProjectCost projectCost = new ProjectCost(projectName, phaseCosts, phaseCostDetails);

        projectCostProcess.computeProjectCost(projectCost);

        return projectCost;
    }

    @Override
    public List<String> getPhase(String projectName) {
        log.info(projectName + "获取阶段");
        List<String> list = new ArrayList<>();
        list.add(PhaseEnum.NPRB.getPhase());
        list.add(PhaseEnum.Design.getPhase());

        list.add(PhaseEnum.DVT.getPhase() + "-1");
        list.add(PhaseEnum.DVT.getPhase() + "-2");
        list.add(PhaseEnum.DVT.getPhase() + "-3");
        list.add(PhaseEnum.DVT.getPhase() + "-4");
        list.add(PhaseEnum.DVT.getPhase() + "-5");
        list.add(PhaseEnum.DVT.getPhase() + "-6");

        list.add(PhaseEnum.PVT.getPhase());
        return list;
    }
}
