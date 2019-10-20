package com.ivo.test.coq.verification;

import com.ivo.modules.coq.service.verification.VerificationSubjectAndMachineService;
import com.ivo.test.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wj
 * @version 1.0
 */
@Transactional
@Rollback(false)
public class VerificationSubjectAndMachineServiceTest extends AbstractTest {

    @Autowired
    private VerificationSubjectAndMachineService verificationSubjectAndMachineService;

    @Test
    public void test() {

        verificationSubjectAndMachineService.syncElectricityBillPer();

    }
}
