package com.ivo;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: wj
 * @Date: 2019-06-05 14:27
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AbstractTest {
    /**
     * 开始测试
     */
    @BeforeClass
    public static void setUpForClass() {
        System.out.println(" ");
        System.out.println("++++++++ 开始测试 ++++++++");
    }

    /**
     * 结束测试
     */
    @AfterClass
    public static void testOverForClass() {
        System.out.println(" ");
        System.out.println("-------- 结束测试 --------");
    }
}
