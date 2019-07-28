package com.ivo.test.coq;

import com.ivo.modules.coq.domain.ProjectCost;
import com.ivo.modules.coq.service.ProjectCostService;
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
    private ProjectCostService projectCostService;

    @Test
    public void test() {
        ProjectCost projectCost = projectCostService.getProjectCost("S0171 R0");
        Assert.assertNotNull(projectCost);
    }

}
