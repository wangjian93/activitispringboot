package com.ivo.test.validate;

import com.ivo.modules.coq.service.ValidateService;
import com.ivo.test.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: wj
 * @Date: 2019-09-08 19:16
 * @Version 1.0
 */
public class ValidateServiceTest extends AbstractTest {

    @Autowired
    private ValidateService validateService;

    @Test
    public void test() {
        validateService.syncValidateBase(2017);
        validateService.syncValidateSubject(2017);
    }
}
