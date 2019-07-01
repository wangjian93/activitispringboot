package com.ivo.common.utils;

import com.ivo.common.data.URL;
import com.ivo.common.enums.ResultEnum;
import com.ivo.common.vo.ResultVo;

/**
 * 响应数据(结果)最外层对象工具
 * @Author: wj
 * @Date: 2019-05-31 08:46
 * @Version 1.0
 */
public class ResultVoUtil {


    /**
     * 操作成功
     * @param msg 提示信息
     * @param object 结果对象
     * @return
     */
    public static ResultVo success(String msg, Object object) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(ResultEnum.SUCCESS.getCode());
        resultVo.setMsg(msg);
        resultVo.setData(object);
        return resultVo;
    }

    /**
     * 操作成功，返回url地址
     * @param msg 提示信息
     * @param url URL包装对象
     * @return
     */
    public static ResultVo success(String msg, URL url) {
        String u = url.getUrl();
        return success(msg, u);
    }

    /**
     * 操作成功，使用默认的提示信息
     * @param object 对象
     * @return
     */
    public static ResultVo success(Object object) {
        String msg = ResultEnum.SUCCESS.getMessage();
        return success(msg, object);
    }

    /**
     * 操作成功，返回提示信息，不返回数据
     * @param msg
     * @return
     */
    public static ResultVo success(String msg) {
        Object object = null;
        return success(msg, object);
    }

    /**
     * 操作成功，使用默认的提示信息，不返回数据
     */
    public static ResultVo success(){
        String msg = ResultEnum.SUCCESS.getMessage();
        return success(msg);
    }

    /**
     * 操作有误
     * @param code 错误码
     * @param msg 提示信息
     * @return
     */
    public static ResultVo error(Integer code, String msg) {
        ResultVo resultVo = new ResultVo();
        resultVo.setMsg(msg);
        resultVo.setCode(code);
        return resultVo;
    }

    /**
     * 操作有误，使用默认400错误码
     * @param msg 提示信息
     * @return
     */
    public static ResultVo error(String msg) {
        Integer code = ResultEnum.ERROR.getCode();
        return error(code, msg);
    }

    /**
     * 操作有误，使用默认400错误码，使用默认的提示信息
     * @return
     */
    public static ResultVo erro() {
        String msg = ResultEnum.ERROR.getMessage();
        return error(msg);
    }
}
