package com.ivo.test.coq;

import com.ivo.modules.eifapi.service.BmCostService;
import com.ivo.test.AbstractTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author: wj
 * @Date: 2019-08-19 08:50
 * @Version 1.0
 */
@Transactional
public class BmServiceTest extends AbstractTest {

    @Autowired
    private BmCostService bmCostService;

    @Test
    public void test() {
        Double d = bmCostService.getGudingzichan(new Date(), new Date(), "");
        Assert.assertNotNull(d);
    }
}
