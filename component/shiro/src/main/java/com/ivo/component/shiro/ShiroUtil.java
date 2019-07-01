package com.ivo.component.shiro;

import com.ivo.common.utils.EncryptUtil;
import com.ivo.modules.system.domain.User;
import org.apache.shiro.SecurityUtils;

/**
 * Shiro工具类
 * @Author: wj
 * @Date: 2019-06-05 14:51
 * @Version 1.0
 */
public class ShiroUtil {

    /**
     * 加密算法
     */
    public final static String hashAlgorithmName = EncryptUtil.hashAlgorithmName;

    /**
     * 循环次数
     */
    public final static int hashIterations = EncryptUtil.hashIterations;

    /**
     * 加密处理
     * 备注：采用自定义的密码加密方式，其原理与SimpleHash一致，
     * 为的是在多个模块间可以使用同一套加密方式，方便共用系统用户。
     * @param password 密码
     * @param salt 密码盐
     */
    public static String encrypt(String password, String salt) {
        return EncryptUtil.encrypt(password, salt, hashAlgorithmName, hashIterations);
    }

    /**
     * 获取随机盐值
     */
    public static String getRandomSalt(){
        return EncryptUtil.getRandomSalt();
    }

    /**
     * 获取ShiroUser对象
     */
    public static User getSubject(){
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 获取用户IP地址
     */
    public static String getIp(){
        return SecurityUtils.getSubject().getSession().getHost();
    }
}
