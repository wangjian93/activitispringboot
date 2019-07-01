package com.ivo.test.component.thymleaf;

import com.ivo.AbstractTest;
import com.ivo.component.thymleaf.utility.DictUtil;
import org.apache.shiro.util.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * @Author: wj
 * @Date: 2019-06-12 08:38
 * @Version 1.0
 */
public class DictUtilTest extends AbstractTest {

    @Test
    public void test() {
        Map map = DictUtil.value("DATA_STATUS");
        Assert.notEmpty(map);
    }
}
