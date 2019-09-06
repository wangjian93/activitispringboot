package com.ivo.modules.coq.service;

import com.ivo.modules.coq.domain.BmCost;

/**
 * @Author: wj
 * @Date: 2019-08-19 09:11
 * @Version 1.0
 */
public interface BmService {

    BmCost getBmCost(String project, String stage);

    void saveBmCost(BmCost bmCost);
}
