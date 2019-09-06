package com.ivo.modules.coq.service.impl;

import com.ivo.modules.coq.cost.DoubleUtil;
import com.ivo.modules.coq.domain.EEProjectCost;
import com.ivo.modules.coq.domain.PlmProjectStage;
import com.ivo.modules.coq.enums.FactoryTypeEnum;
import com.ivo.modules.coq.enums.StageTypeEnum;
import com.ivo.modules.coq.repository.EEProjectCostRepository;
import com.ivo.modules.coq.rest.RestService;
import com.ivo.modules.coq.service.EEProjectCostService;
import com.ivo.modules.coq.service.PlmProjectStageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: wj
 * @Date: 2019-08-23 10:43
 * @Version 1.0
 */
@Service
public class EEProjectCostServiceImpl implements EEProjectCostService {

    @Autowired
    private EEProjectCostRepository repository;

    @Autowired
    private PlmProjectStageService stageService;

    @Autowired
    private RestService restService;

    @Override
    public List<EEProjectCost> getEEProjectCost(String project) {
        return repository.findByProject(project);
    }

    @Override
    public EEProjectCost getEEProject(String project, String stage, String process) {
        return repository.findDistinctByProjectAndStageAndProcess(project, stage, process);
    }

    @Override
    public List<EEProjectCost> syncFromEeBomWo(String project) {
        // 删除原始数据
        deleteEEProjectCost(getEEProjectCost(project));

        // 同步数据
        List<PlmProjectStage> stageList = stageService.getPlmStageList(project);
        if(stageList == null) {
            return null;
        }

        List<EEProjectCost> newList = new ArrayList<>();

        for(PlmProjectStage stage : stageList) {
            if(StringUtils.equalsAnyIgnoreCase(stage.getStage(), StageTypeEnum.NPRB.getStage(), StageTypeEnum.DESIGN.getStage())) {
                continue;
            }

            EEProjectCost eeProjectCost = new EEProjectCost(stage.getProject(), stage.getStage(), stage.getProcess(), stage.getEdOrEeOrOrderNumber());
            handleFactory(eeProjectCost);
            handleEDAndEE(eeProjectCost);
            if(StringUtils.equalsIgnoreCase(eeProjectCost.getFactory(), FactoryTypeEnum.ARRAY.getFactory())) {
                handleProdID(eeProjectCost);
                handleMaterialAndVersion(eeProjectCost);
                handleMaterialPrice(eeProjectCost);
            }
            if(StringUtils.equalsIgnoreCase(eeProjectCost.getFactory(), FactoryTypeEnum.CELL.getFactory())) {
                handlePfcd(eeProjectCost);
                handleMaterialAndVersion(eeProjectCost);
                handleMaterialPrice(eeProjectCost);
            }
            if(StringUtils.equalsIgnoreCase(eeProjectCost.getFactory(), FactoryTypeEnum.MODULE.getFactory())) {
                handleWo(eeProjectCost);
                handleWoCost(eeProjectCost);
            }
            newList.add(eeProjectCost);
        }

        saveEEProjectCost(newList);
        return getEEProjectCost(project);
    }

    private void deleteEEProjectCost(List<EEProjectCost> list) {
        repository.deleteAll(list);
    }

    private void saveEEProjectCost(List<EEProjectCost> list) {
        repository.saveAll(list);
    }

    /**
     * 确定工厂
     * @param eeProjectCost
     */
    private void handleFactory(EEProjectCost eeProjectCost) {
        // TODO... 确定工厂
        String process = eeProjectCost.getProcess();
        String factory = "";
        if(StringUtils.equalsIgnoreCase(process, FactoryTypeEnum.CELL.getFactory())) {
            factory = FactoryTypeEnum.CELL.getFactory();
        } else if(StringUtils.equalsIgnoreCase(process, FactoryTypeEnum.ARRAY.getFactory())) {
            factory = FactoryTypeEnum.ARRAY.getFactory();
        } else if(StringUtils.equalsIgnoreCase(process, FactoryTypeEnum.MODULE.getFactory())) {
            factory = FactoryTypeEnum.MODULE.getFactory();
        }
        eeProjectCost.setFactory(factory);
    }

    /**
     * 确定DE EE
     * @param eeProjectCost
     */
    private void handleEDAndEE(EEProjectCost eeProjectCost) {
        String orderNumber = eeProjectCost.getOrderNumber();
        if(StringUtils.contains(orderNumber, "EE")) {
            eeProjectCost.setEeOrder(orderNumber);
        } else if(StringUtils.contains(orderNumber, "ED")) {
            eeProjectCost.setEdOrder(orderNumber);
            eeProjectCost.setEeOrder(getEEFromED(orderNumber));
        }
    }

    /**
     * 从ED获取到EE
     * @param ed
     * @return
     */
    private String getEEFromED(String ed) {
        // TODO... 从ED获取到EE
        return restService.getEeFromEd(ed);
    }

    /**
     * 确定ProdID
     * @param eeProjectCost
     */
    private void handleProdID(EEProjectCost eeProjectCost) {
        String ee = eeProjectCost.getEeOrder();
        eeProjectCost.setProdId(getProdIdFromEE(ee));
    }

    /**
     * 从EE获取ProdId
     * @param ee
     * @return
     */
    private String getProdIdFromEE(String ee) {
        // TODO... 从EE获取到ProdId
        return restService.getProdIdFromEE(ee);
    }

    /**
     * 确定PFCD TFT CF
     * @param eeProjectCost
     */
    private void handlePfcd(EEProjectCost eeProjectCost) {
        String ee = eeProjectCost.getEeOrder();
        Map<String, String> map = getPfcdFromEE(ee);
        eeProjectCost.setPfcd(map.get("pfcd"));
        eeProjectCost.setPfcd_tft(map.get("tft"));
        eeProjectCost.setPfcd_cf(map.get("cf"));
    }

    /**
     * 从EE获取PFCD TFT CF
     * @param ee
     * @return
     */
    private Map<String, String> getPfcdFromEE(String ee) {
        // TODO... 从EE获取PFCD TFT CF
        return restService.getPfcdFromEE(ee);
    }

    /**
     * 确定wo工单
     * @param eeProjectCost
     */
    private void handleWo(EEProjectCost eeProjectCost) {
        String ee = eeProjectCost.getEeOrder();
        List<String> list = getWoFromEE(ee);
        if(list==null || list.size()==0) {
            return;
        }

        String wos = "";
        for(String wo : list) {
            if(!wos.equals("")) {
                wos += ",";
            }
            wos += wo;
        }
        eeProjectCost.setWo(wos);
    }

    /**
     * 从EE获取工单号
     * @param ee
     * @return
     */
    private List<String> getWoFromEE(String ee) {
        return restService.getWoFromEE(ee);
    }

    /**
     * Array/CELL 确定料号和版本
     * @param eeProjectCost
     */
    private void handleMaterialAndVersion(EEProjectCost eeProjectCost) {
        // Array的为prodId  Cell的为pfcd
        String factory = eeProjectCost.getFactory();
        String prodId = "";
        if(StringUtils.equalsIgnoreCase(factory, FactoryTypeEnum.ARRAY.getFactory())) {
            prodId = eeProjectCost.getProdId();
        } else if(StringUtils.equalsIgnoreCase(factory, FactoryTypeEnum.CELL.getFactory())) {
            prodId = eeProjectCost.getPfcd();
        }
        Map<String, String> map = getMaterialAndVersion(prodId);
        eeProjectCost.setMaterial(map.get("MATNR"));
        eeProjectCost.setVersion(map.get("VERID"));
    }

    /**
     * 从Bom中获取料号和版本
     * @param prodId
     * @return
     */
    Map<String, String> getMaterialAndVersion(String prodId) {
        return restService.getMaterialAndVersion(prodId);
    }

    /**
     * Array/CELL 确定料号价格
     * @param eeProjectCost
     */
    private void handleMaterialPrice(EEProjectCost eeProjectCost) {
        // Array的为prodId  Cell的为pfcd
        String factory = eeProjectCost.getFactory();
        String prodId;
        Double price = null;
        if(StringUtils.equalsIgnoreCase(factory, FactoryTypeEnum.ARRAY.getFactory())) {
            prodId = eeProjectCost.getProdId();
            price = restService.getMaterialPriceForArrayFromBom(prodId);
        } else if(StringUtils.equalsIgnoreCase(factory, FactoryTypeEnum.CELL.getFactory())) {
            prodId = eeProjectCost.getPfcd();
            price = restService.getMaterialPriceForCellFromBom(prodId);
        }
        eeProjectCost.setMaterialPrice(price);
    }

    /**
     * 模组 确定工单中的费用
     * @param eeProjectCost
     */
    private void handleWoCost(EEProjectCost eeProjectCost) {
        String[] wos = eeProjectCost.getWo().split(",");
        Double sum = null;
        for(String wo : wos) {
            Double woCost = restService.getWoCostForModuleFromBom(wo);
            sum = DoubleUtil.sum(sum, woCost);
        }
        eeProjectCost.setWoCost(sum);
    }
}
