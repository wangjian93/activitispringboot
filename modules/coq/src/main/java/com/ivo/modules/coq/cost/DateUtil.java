package com.ivo.modules.coq.cost;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * @Author: wj
 * @Date: 2019-09-02 00:12
 * @Version 1.0
 */
public class DateUtil {

    public String fmtDate(Date date) {
        if(date == null) {
            return null;
        }
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
