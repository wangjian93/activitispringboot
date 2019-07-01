package com.ivo.test.common.utils;

import com.ivo.component.shiro.ShiroUtil;
import org.junit.Test;

/**
 * 密码加密测试类，可用于重置密码
 * @Author: wj
 * @Date: 2019-06-05 14:34
 * @Version 1.0
 */
public class EncryptUtilTest {

    @Test
    public void encrypt() {
//        String password = "123456";
//        String salt = "ivo";
//
//        String encrypt = EncryptUtil.encrypt(password, salt);
//        System.out.println("明文密码：" + password);
//        System.out.println("密码盐：" + salt);
//        System.out.println("混淆密码：" + encrypt);

        String salt = ShiroUtil.getRandomSalt();
        String encrypt = ShiroUtil.encrypt("123456", salt);
        System.out.println("密码盐：" + salt);
        System.out.println("混淆密码：" + encrypt);
    }
}
