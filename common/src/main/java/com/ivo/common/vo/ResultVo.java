package com.ivo.common.vo;

import lombok.Data;

/**
 * 响应数据(结果)最外层对象
 * @Author: wj
 * @Date: 2019-05-31 08:41
 * @Version 1.0
 */
@Data
public class ResultVo<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 响应数据的总量，用于分页
     */
    private long count;

}
