package com.ivo.test.coq;

import com.ivo.modules.coq.domain.ProjectCost;
import com.ivo.modules.coq.service.PlmProjectStageService;
import com.ivo.modules.coq.service.ProjectCostService;
import com.ivo.modules.coq.service.ProjectCostService2;
import com.ivo.modules.coq.service.verification.VerificationSubjectAndMachineService;
import com.ivo.modules.hr.repository.TestRepository;
import com.ivo.test.AbstractTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: wj
 * @Date: 2019-07-25 15:51
 * @Version 1.0
 */
@Transactional
public class ProjectCostServiceTest extends AbstractTest {



    @Autowired
    private VerificationSubjectAndMachineService subjectAndMachineService;

    @Autowired
    private PlmProjectStageService stageService;

    @Test
    public void test2() {
        subjectAndMachineService.syncElectricityBillPer();
    }

    @Test
    public void test() {
        stageService.syncStageFromPlm("N1408 R0");
    }

}
