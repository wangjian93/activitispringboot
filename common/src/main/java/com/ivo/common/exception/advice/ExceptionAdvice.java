package com.ivo.common.exception.advice;

/**
 * 异常通知器接口
 * @Author: wj
 * @Date: 2019-06-01 16:10
 * @Version 1.0
 */
public interface ExceptionAdvice {
    void run(RuntimeException e);
}
