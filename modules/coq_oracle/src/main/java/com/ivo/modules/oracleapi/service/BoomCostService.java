package com.ivo.modules.oracleapi.service;

import java.util.Map;

/**
 * 根据机种的料号从Boom中获取材料成本
 * @Author: wj
 * @Date: 2019-07-30 14:15
 * @Version 1.0
 */
public interface BoomCostService {

    /**
     * ARY 大板
     * @param material 料号
     * @param version 版本
     * @return
     */
    Double getPriceForArray(String material, String version);

    /**
     * Cell 大板
     * @param material 料号
     * @param version 版本
     * @return
     */
    Double getPriceForCell(String material, String version);

    /**
     * Module 小板
     * @param material 料号
     * @param version 版本
     * @return
     */
    Double getPriceForModule(String material, String version);


    Double getPriceForArray(String productId);

    Double getPriceForCell(String productId);

    Double getWoCost(String wo);

    Map getMaterialAndVersion(String prodId);

}
