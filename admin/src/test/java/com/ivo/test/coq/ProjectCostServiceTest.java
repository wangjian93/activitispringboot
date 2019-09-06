package com.ivo.test.coq;

import com.ivo.modules.coq.domain.ProjectCost;
import com.ivo.modules.coq.service.ProjectCostService;
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
    private ProjectCostService projectCostService;

    @Autowired
    private TestRepository testRepository;

    @Test
    public void test() {
        ProjectCost projectCost = projectCostService.getProjectCost("S0171 R0");
        Assert.assertNotNull(projectCost);
    }

    @Test
    public void test2() {
        Double d1 = testRepository.sqlQuery1("1514015-00", "PP02");

        Double d2 = testRepository.sqlQuery2("141408000-00", "E01");

        Double d3 = testRepository.sqlQuery3("M140NWR8-0G1", "E13");
        Assert.assertNotNull(d1);
    }

}
