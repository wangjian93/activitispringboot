package com.ivo.modules.coq.service.impl;

import com.ivo.modules.coq.cost.DoubleUtil;
import com.ivo.modules.coq.domain.MaterialPrice;
import com.ivo.modules.coq.repository.MaterialPriceRepository;
import com.ivo.modules.coq.service.MaterialPriceService;
import com.ivo.modules.oracleapi.service.BoomCostService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: wj
 * @Date: 2019-07-30 16:42
 * @Version 1.0
 */
@Service
@Slf4j
public class MaterialPriceServiceImpl implements MaterialPriceService {

    @Autowired
    private MaterialPriceRepository repository;

    @Autowired
    private BoomCostService boomCostService;


    @Override
    public MaterialPrice getMaterialPrice(String orderNumber) {
        if(StringUtils.isEmpty(orderNumber)) {
            return null;
        }

        log.info("获取[" + orderNumber + "]对应的boom材料成本价格");
        MaterialPrice materialPrice = repository.findDistinctByOrderNumber(orderNumber);

        if(materialPrice == null) {
            log.info("单号[" + orderNumber + "]对应的boom材料价格系统不存在，开始生成");
            materialPrice = generateMaterialPrice(orderNumber);
        }

        // 检查价格
        if(materialPrice.getPrice() == null){
            log.info("单号[" + orderNumber + "]对应的boom材料价格为空，更新价格");
            updateMaterialPrice(materialPrice);
        }

        return materialPrice;
    }

    public MaterialPrice generateMaterialPrice(String orderNumber) {
        log.info("生成EE/ED单号[" + orderNumber + "]对应的boom材料成本价格... start");
        MaterialPrice materialPrice = new MaterialPrice(orderNumber);
        generateOrUpdateMaterialPrice(materialPrice);
        log.info("生成材料成本价格成功... end");
        return materialPrice;
    }

    public MaterialPrice updateMaterialPrice(String orderNumber) {
        MaterialPrice materialPrice = repository.findDistinctByOrderNumber(orderNumber);
        updateMaterialPrice(materialPrice);
        return materialPrice;
    }

    public MaterialPrice updateMaterialPrice(MaterialPrice materialPrice) {
        log.info("更新EE/ED单号[" + materialPrice.getOrderNumber() + "]对应的boom材料成本价格... start");
        generateOrUpdateMaterialPrice(materialPrice);
        log.info("更新材料成本价格成功... end");
        return materialPrice;
    }

    public void generateOrUpdateMaterialPrice(MaterialPrice materialPrice) {
        setEeByEd(materialPrice);

        setPlant(materialPrice);

        setWo(materialPrice);
        setProdId(materialPrice);
        setPfcd(materialPrice);

        setWoCost(materialPrice);

        setMaterialAndVersion(materialPrice);
        setMaterialBoomPrice(materialPrice);


        saveMaterialPrice(materialPrice);
    }


    private void saveMaterialPrice(MaterialPrice materialPrice) {
        log.info("--持久化["+materialPrice.getOrderNumber()+"]材料成本价格");
        repository.save(materialPrice);
    }

    /**
     * 关联ED单到EE单
     * @param materialPrice
     */
    private void setEeByEd(MaterialPrice materialPrice) {
        // TODO... 通过ED获取关联的EE
        log.info("--获取["+materialPrice.getOrderNumber()+"]单关联信息，根据ED关联到EE");

        if(StringUtils.isEmpty(materialPrice.getEdOrder())) {
            return;
        }

        switch (materialPrice.getEdOrder()) {
            case "ED180300010" : materialPrice.setEeOrder("EE180300129"); break;
            case "ED180300023" : materialPrice.setEeOrder("EE180300249"); break;
            case "ED180500002" : materialPrice.setEeOrder("EE180500015"); break;
            case "ED180500010" : materialPrice.setEeOrder("EE180500215"); break;
            case "ED180600019" : materialPrice.setEeOrder("EE180600139"); break;
            case "ED180700009" : materialPrice.setEeOrder("EE180600155"); break;
            case "ED190200010" : materialPrice.setEeOrder("EE190200188"); break;
            case "ED190600014" : materialPrice.setEeOrder("EE190600099"); break;
            case "ED190700005" : materialPrice.setEeOrder("EE190700309"); break;
        }
    }

    /**
     * EE单获取厂别
     * @param materialPrice
     */
    private void setPlant(MaterialPrice materialPrice) {
        // TODO... 获取ED、EE所属厂别Array cell Module
        log.info("-获取["+materialPrice.getOrderNumber()+"]单关联信息，获取对应厂别");

        String[] array = {"EE180300129", "EE180500015", "EE180600139", "EE190200188"};
        String[] cell = {"EE180300249", "EE180500215", "EE180600155", "EE190600099", "EE190700309"};
        String[] module = {"EE180500053", "EE180800226", "EE180700176", "EE181200519"};

        if(ArrayUtils.contains(array, materialPrice.getEeOrder())) {
            materialPrice.setPlant("Array");
        }
        if(ArrayUtils.contains(cell, materialPrice.getEeOrder())) {
            materialPrice.setPlant("Cell");
        }
        if(ArrayUtils.contains(module, materialPrice.getEeOrder())) {
            materialPrice.setPlant("Module");
        }

    }

   private void setWo(MaterialPrice materialPrice) {
        if(StringUtils.equals(materialPrice.getPlant(), "Module")) {
            switch (materialPrice.getEeOrder()) {
                case "EE180500053" : materialPrice.setWo("IL2M185117,IL2M185118"); break;
                case "EE180800226" : materialPrice.setWo("IL2M188120"); break;
                case "EE180700176" : materialPrice.setWo("IL2M187108,IL2M187109"); break;
                case "EE181200519" : materialPrice.setWo("IL2M191110,IL2M191111,IL2M191112,IL2M191113"); break;
            }
        }
   }

    private void setProdId(MaterialPrice materialPrice) {
        if(StringUtils.equals(materialPrice.getPlant(), "Array")) {
            switch (materialPrice.getEeOrder()) {
                case "EE180300129" : materialPrice.setProductId("AE14J4A01N"); break;
                case "EE180500015" : materialPrice.setProductId("AE14J4A01N"); break;
                case "EE180600139" : materialPrice.setProductId("AE14J4A02N"); break;
                case "EE190200188" : materialPrice.setProductId("AE14J4A02N"); break;
            }
        }
    }

    private void setPfcd(MaterialPrice materialPrice) {
        if(StringUtils.equals(materialPrice.getPlant(), "Cell")) {
            switch (materialPrice.getEeOrder()) {
                case "EE180300249" : materialPrice.setPfcd("E140TDHAB0"); break;
                case "EE180500215" : materialPrice.setPfcd("E140TDHAB0"); break;
                case "EE180600155" : materialPrice.setPfcd("E140TDHAB1"); break;
                case "EE190600099" : materialPrice.setPfcd("E140TDHAB1"); break;
                case "EE190700309" : materialPrice.setPfcd("E140TDHAB1"); break;
            }
        }

    }

    private void setWoCost(MaterialPrice materialPrice) {
        if(StringUtils.isEmpty(materialPrice.getWo())) {
            return;
        }
        String[] wos = materialPrice.getWo().split(",");
        Double d = null;
        for(String wo : wos) {
            Double cost = boomCostService.getWoCost(wo);
            d = DoubleUtil.sum(d, cost);
        }
        materialPrice.setWoCost(d);
    }

    /**
     * EE（Array/Cell）获取对应的料号和版本
     * @param materialPrice
     */
    private void setMaterialAndVersion(MaterialPrice materialPrice) {
        // TODO... 根据ED、EE获取到料号和版本 VERID  MATNR
        log.info("--获取["+materialPrice.getOrderNumber()+"]材料成本价格，根据ED/EE获取料号、版本");

        if(materialPrice.getPlant().equals("Array")) {
            Map map = boomCostService.getMaterialAndVersion(materialPrice.getProductId());
            materialPrice.setMaterial(String.valueOf(map.get("MATNR")));
            materialPrice.setVersion(String.valueOf(map.get("VERID")));
        } else if(materialPrice.getPlant().equals("Cell")) {
            Map map = boomCostService.getMaterialAndVersion(materialPrice.getPfcd());
            materialPrice.setMaterial(String.valueOf(map.get("MATNR")));
            materialPrice.setVersion(String.valueOf(map.get("VERID")));
        }

//        switch (materialPrice.getOrderNumber()) {
//            case "ED180300023" : materialPrice.setMaterial("141408000-01"); materialPrice.setVersion("E01"); materialPrice.setPlant("Cell"); break;
//            case "ED180700009" : materialPrice.setMaterial("141408000-01"); materialPrice.setVersion("E01"); materialPrice.setPlant("Cell"); break;
//            case "ED190600014" : materialPrice.setMaterial("141408001-00"); materialPrice.setVersion("E01"); materialPrice.setPlant("Cell"); break;
//            case "ED190700005" : materialPrice.setMaterial("141408000-01"); materialPrice.setVersion("E01"); materialPrice.setPlant("Cell"); break;
//        }
    }

    private void setMaterialBoomPrice(MaterialPrice materialPrice) {
        log.info("--获取["+materialPrice.getOrderNumber()+"]材料成本价格，根据料号、版本获取boom材料成本价格");

        if(materialPrice.getPlant().equals("Array")) {
            materialPrice.setPrice(
                    boomCostService.getPriceForArray(materialPrice.getProductId())
            );
        } else if(materialPrice.getPlant().equals("Cell")) {
            materialPrice.setPrice(
                    boomCostService.getPriceForCell(materialPrice.getPfcd())
            );
        }

//        String mtrl = materialPrice.getMaterial();
//        String ver = materialPrice.getVersion();
//        String plant = materialPrice.getPlant();
//
//        if(mtrl== null || ver == null) {
//            log.warn("单号["+materialPrice.getOrderNumber()+"]未能关联到料号和版本");
//            return;
//        }
//
//        if(StringUtils.equals(plant.toUpperCase(), "ARRAY")) {
//            materialPrice.setPrice(boomCostService.getPriceForArray(mtrl, ver));
//        } else if(StringUtils.equals(plant.toUpperCase(), "CELL")) {
//            materialPrice.setPrice(boomCostService.getPriceForCell(mtrl, ver));
//        } else {
//            materialPrice.setPrice(boomCostService.getPriceForModule(mtrl, ver));
//        }
    }
}
