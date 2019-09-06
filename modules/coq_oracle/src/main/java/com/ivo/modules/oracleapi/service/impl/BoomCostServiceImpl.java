package com.ivo.modules.oracleapi.service.impl;

import com.ivo.modules.oracleapi.repository.BoomCostRepository;
import com.ivo.modules.oracleapi.service.BoomCostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Boom材料成本
 * @Author: wj
 * @Date: 2019-07-30 14:34
 * @Version 1.0
 */
@Service
@Slf4j
public class BoomCostServiceImpl implements BoomCostService {

    @Autowired
    private BoomCostRepository repository;

    /**
     * Array 大板
     * @param material 料号
     * @param version 版本
     * @return
     */
    @Override
    public Double getPriceForArray(String material, String version) {
        log.info("获取[" + material + " "+version+"]Boom材料成本价格  [Array]");
        return repository.getPriceForArray(material, version);
    }

    /**
     * Cell 大板
     * @param material 料号
     * @param version 版本
     * @return
     */
    @Override
    public Double getPriceForCell(String material, String version) {
        log.info("获取[" + material + " "+version+"]Boom材料成本价格  [Cell]");
        return repository.getPriceForCell(material, version);
    }

    /**
     * Module 小板
     * @param material 料号
     * @param version 版本
     * @return
     */
    @Override
    public Double getPriceForModule(String material, String version) {
        log.info("获取[" + material + " "+version+"]Boom材料成本价格  [Module]");
        return repository.getPriceForModule(material, version);
    }


    @Override
    public Double getPriceForArray(String productId) {
        return repository.getPriceForArray(productId);
    }

    @Override
    public Double getPriceForCell(String productId) {
        return repository.getPriceForCell(productId);
    }

    @Override
    public Double getWoCost(String wo) {
        return repository.getWoCost(wo);
    }

    @Override
    public Map getMaterialAndVersion(String prodId) {
        return repository.getMaterialAndVersion(prodId);
    }
}
