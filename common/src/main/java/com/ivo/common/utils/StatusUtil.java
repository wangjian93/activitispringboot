package com.ivo.common.utils;

import com.ivo.common.constant.StatusConst;
import com.ivo.common.enums.ResultEnum;
import com.ivo.common.enums.StatusEnum;
import com.ivo.common.exception.ResultException;

/**
 * 数据状态工具
 * @Author: wj
 * @Date: 2019-07-03 16:37
 * @Version 1.0
 */
public class StatusUtil {

    /**
     * 逻辑删除语句
     */
    public static final String sliceDelete = " set valid_Flag=" + StatusConst.DELETE + " WHERE id=?";

    /**
     * 不等于逻辑删除条件语句
     */
    public static final String notDelete = "valid_Flag != " + StatusConst.DELETE;

    /**
     * 获取状态StatusEnum对象
     * @param param 状态字符参数
     */
    public static StatusEnum getStatusEnum(String param){
        try {
            return StatusEnum.valueOf(param.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ResultException(ResultEnum.STATUS_ERROR);
        }
    }
}
