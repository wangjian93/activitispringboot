package com.ivo.test.coq;

import com.ivo.modules.coq.rest.RestService;
import com.ivo.test.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: wj
 * @Date: 2019-07-25 13:02
 * @Version 1.0
 */
public class RestServiceTest extends AbstractTest {

    @Autowired
    private RestService restService;

    @Test
    public void test() {
        List list = restService.getProjectStage("S0171 R0");
    }
}
