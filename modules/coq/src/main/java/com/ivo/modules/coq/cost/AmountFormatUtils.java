package com.ivo.modules.coq.cost;

import java.text.DecimalFormat;

/**
 * 金额工具类
 * @Author: wj
 * @Date: 2019-09-01 23:39
 * @Version 1.0
 */
public class AmountFormatUtils {

    /**
     * 格式化数字为千分位显示；
     * @param d 要格式化的数字
     * @return
     */
    public String fmtThousands(Double d) {
        if (d == null) {
            return null;
        }
        String text = String.valueOf(d);
        DecimalFormat df;
        if(text.indexOf(".") > 0) {
            int i = text.length() - text.indexOf(".")-1;
            if(i == 0)
            {
                df = new DecimalFormat("###,##0.");
            } else if(i == 1) {
                df = new DecimalFormat("###,##0.0");
            } else {
                df = new DecimalFormat("###,##0.00");
            }
        } else {
            df = new DecimalFormat("###,##0");
        }
        double number;
        try {
            number = Double.parseDouble(text);
        } catch (Exception e) {
            number = 0.0;
        }
        return df.format(number);
    }
}
