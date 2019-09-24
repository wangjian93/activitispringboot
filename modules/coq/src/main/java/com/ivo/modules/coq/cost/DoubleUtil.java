package com.ivo.modules.coq.cost;

import java.math.BigDecimal;

/**
 * Double类型计算工具
 * @Author: wj
 * @Date: 2019-07-02 14:12
 * @Version 1.0
 */
public class DoubleUtil {

    // 保留小数
    private static final int SCALE = 2;
    // 舍入模式，四舍五入
    private static final int ROUND = BigDecimal.ROUND_HALF_UP;


    /**
     * 求和
     * @param values
     * @return
     */
    public static Double sum(Double... values) {

        Double d = null;
        BigDecimal bigDecimal = new BigDecimal(0);
        for (int i = 0; i <  values.length; i++) {
            Double v = values[i];
            if(v == null) {
                continue;
            } else {
                bigDecimal = bigDecimal.add(new BigDecimal(v));
                d = bigDecimal.setScale(SCALE, ROUND).doubleValue();
            }
        }

        return d;
    }

    /**
     * 相乘
     * @param values
     * @return
     */
    public static Double multiply(Double... values) {
        Double d = null;
        BigDecimal bigDecimal = new BigDecimal(1);
        for (int i = 0; i <  values.length; i++) {
            Double v = values[i];
            if(v == null) {
                return null;
            } else {
                bigDecimal = bigDecimal.multiply(new BigDecimal(v));
                d = bigDecimal.setScale(SCALE, ROUND).doubleValue();
            }
        }

        return d;
    }

    /**
     * 相除
     * @param numerator 分子
     * @param denominator 分母
     * @return
     */
    public static Double divide(Double numerator, Double denominator) {
        if(numerator == null || denominator == null) {
            return null;
        } else {
            BigDecimal bigDecimal1 = new BigDecimal(numerator);
            BigDecimal bigDecimal2 = new BigDecimal(denominator);
            return bigDecimal1.divide(bigDecimal2, SCALE, ROUND).doubleValue();
        }
    }

}
