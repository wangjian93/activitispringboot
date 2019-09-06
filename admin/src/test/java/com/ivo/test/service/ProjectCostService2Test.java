package com.ivo.test.service;

import com.ivo.modules.coq.service.ProjectCostService2;
import com.ivo.test.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: wj
 * @Date: 2019-09-01 17:44
 * @Version 1.0
 */
public class ProjectCostService2Test extends AbstractTest {

    @Autowired
    private ProjectCostService2 projectCostService;

    @Test
    public void test() {
        projectCostService.syncScheduleFromPlm("N1408 R0");
    }
}
