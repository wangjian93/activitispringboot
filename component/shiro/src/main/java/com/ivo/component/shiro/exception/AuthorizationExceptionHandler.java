package com.ivo.component.shiro.exception;

import com.ivo.common.utils.SpringContextUtil;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截访问权限异常处理
 * @Author: wj
 * @Date: 2019-05-30 13:39
 * @Version 1.0
 */
@ControllerAdvice
@Order(-1)
public class AuthorizationExceptionHandler {

    // 拦截访问权限异常
    @ExceptionHandler(AuthorizationException.class)
    public String authorizationException(AuthorizationException e, HttpServletRequest request,
                                           HttpServletResponse response){

        ShiroFilterFactoryBean shiroFilterFactoryBean = SpringContextUtil.getBean(ShiroFilterFactoryBean.class);

        return "redirect:" + shiroFilterFactoryBean.getUnauthorizedUrl();
    }
}
