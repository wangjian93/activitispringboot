package com.ivo.test.service;

import com.ivo.modules.coq.domain.EEProjectCost;
import com.ivo.modules.coq.service.*;
import com.ivo.test.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: wj
 * @Date: 2019-08-21 10:07
 * @Version 1.0
 */
@Transactional
@Rollback(false)
public class PlmProjectStageServiceTest extends AbstractTest {

    @Autowired
    private PlmProjectStageService stageService;

    @Autowired
    private PlmProjectScheduleService scheduleService;

    @Autowired
    PlmProjectMemberService memberService;

    @Autowired
    BmProjectCostService bmService;

    @Autowired
    EEProjectCostService eeProjectCostService;

    @Test
    public void test() {
        String project = "N1408 R0";

//        stageService.syncStageFromPlm(project);
//
//        scheduleService.syncScheduleFromPlm(project);
//
//        memberService.syncMemberFromPlm(project);
//
//        bmService.syncBmProjectCost(project);

        eeProjectCostService.syncFromEeBomWo(project);
    }
}
