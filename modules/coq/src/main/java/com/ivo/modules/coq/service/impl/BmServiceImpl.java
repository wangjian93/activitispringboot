package com.ivo.modules.coq.service.impl;

import com.ivo.modules.coq.domain.BmCost;
import com.ivo.modules.coq.repository.BmRepository;
import com.ivo.modules.coq.service.BmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wj
 * @Date: 2019-08-19 09:12
 * @Version 1.0
 */
@Service
public class BmServiceImpl implements BmService {


    @Autowired
    private BmRepository bmRepotory;

    @Override
    public BmCost getBmCost(String project, String stage) {
        BmCost bmCost = bmRepotory.findDistinctByProjectAndStage(project, stage);
        return bmCost;
    }

    @Override
    public void saveBmCost(BmCost bmCost) {
        bmRepotory.save(bmCost);
    }
}
