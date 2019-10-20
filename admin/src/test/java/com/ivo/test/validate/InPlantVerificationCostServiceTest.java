package com.ivo.test.validate;

import com.ivo.modules.coq.domain.verification.InPlantVerificationCost;
import com.ivo.modules.coq.repository.verification.InPlantVerificationCostRepository;
import com.ivo.modules.coq.service.impl.verification.InPlantVerificationCostServiceImpl;
import com.ivo.test.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wj
 * @version 1.0
 */
@Transactional
@Rollback(false)
public class InPlantVerificationCostServiceTest extends AbstractTest {

    @Autowired
    private InPlantVerificationCostServiceImpl service;

    @Autowired
    private InPlantVerificationCostRepository repository;

    @Test
    public void test() {
        List<InPlantVerificationCost> list = service.getInPlantVerificationCost("N1566V R0");
        for(InPlantVerificationCost cost : list) {
            service.computeVerification(cost);
            repository.save(cost);
        }
    }
}
