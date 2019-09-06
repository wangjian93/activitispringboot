package com.ivo.modules.coq.service;

import com.ivo.modules.coq.domain.MaterialPrice;

/**
 * 材料成本价格Service
 * @Author: wj
 * @Date: 2019-07-30 15:23
 * @Version 1.0
 */
public interface MaterialPriceService {

    /**
     * 根据申请单号EE或ED获取材料价格
     * @param orderNumber
     * @return
     */
    MaterialPrice getMaterialPrice(String orderNumber);
}
