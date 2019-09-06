package com.ivo.modules.coq.service.impl;

import com.ivo.modules.coq.cost.DoubleUtil;
import com.ivo.modules.coq.domain.BmProjectCost;
import com.ivo.modules.coq.domain.PlmProjectSchedule;
import com.ivo.modules.coq.domain.PlmProjectStage;
import com.ivo.modules.coq.enums.CostTypeEnum;
import com.ivo.modules.coq.enums.StageTypeEnum;
import com.ivo.modules.coq.repository.BmProjectCostRepository;
import com.ivo.modules.coq.rest.RestService;
import com.ivo.modules.coq.service.BmProjectCostService;
import com.ivo.modules.coq.service.PlmProjectScheduleService;
import com.ivo.modules.coq.service.PlmProjectStageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Author: wj
 * @Date: 2019-08-22 10:46
 * @Version 1.0
 */
@Service
@Slf4j
public class BmProjectCostServiceImpl implements BmProjectCostService {

    @Autowired
    private BmProjectCostRepository repository;

    @Autowired
    private RestService restService;

    @Autowired
    private PlmProjectStageService stageService;

    @Autowired
    private PlmProjectScheduleService scheduleService;

    @Override
    public Double getToolCostFromBm(String project, String stage) {
        List<BmProjectCost> detailList = getBmProjectCostList(project, stage, CostTypeEnum.Tool.getCostType());
        if(detailList == null) {
            return null;
        }
        Double amount = null;
        for(BmProjectCost cost : detailList) {
            amount = DoubleUtil.sum(amount, cost.getAmount());
        }
        return amount;
    }

    @Override
    public Double getDirectMaterialCost_thin(String project, String stage) {
        List<BmProjectCost> detailList = getBmProjectCostList(project, stage, CostTypeEnum.DirectMaterial.getCostType());
        if(detailList == null) {
            return null;
        }
        Double amount = null;
        for(BmProjectCost cost : detailList) {
            amount = DoubleUtil.sum(amount, cost.getAmount());
        }
        return amount;
    }

    @Override
    public Double getValidationCost(String project, String stage) {
        List<BmProjectCost> detailList = getBmProjectCostList(project, stage, CostTypeEnum.Validation.getCostType());
        if(detailList == null) {
            return null;
        }
        Double amount = null;
        for(BmProjectCost cost : detailList) {
            amount = DoubleUtil.sum(amount, cost.getAmount());
        }
        return amount;
    }

    @Override
    public List<BmProjectCost> getDetailForToolCost(String project) {
        return repository.findByProjectAndCostType(project, CostTypeEnum.Tool.getCostType());
    }

    @Override
    public List<BmProjectCost> getDetailForDirectMaterialCost_thin(String project) {
        return repository.findByProjectAndCostType(project, CostTypeEnum.DirectMaterial.getCostType());
    }

    @Override
    public List<BmProjectCost> getDetailForValidationCost(String project) {
        return repository.findByProjectAndCostType(project, CostTypeEnum.Validation.getCostType());
    }

    @Override
    public List<BmProjectCost> syncBmProjectCost(String project) {
        // 删除数据
        deleteBmProjectCost(getBmProjectCostList(project));

        List<String> stages = stageService.getStages(project);
        // NPRB 和 DESIGN两个阶段不要算这些成本
        stages.remove(StageTypeEnum.NPRB.getStage());
        stages.remove(StageTypeEnum.DESIGN.getStage());

        Map<String, Map<String, Date>> dateRangeMap = handleDateRange(project);
        for(String stage : stages) {
            Date fromDate = dateRangeMap.get(stage).get("fromDate");
            Date toDate = dateRangeMap.get(stage).get("toDate");
            syncBmProjectCost(project, stage, fromDate, toDate);
        }
        return getBmProjectCostList(project);
    }

    public List<BmProjectCost> syncBmProjectCost(String project, String stage, Date fromDate, Date toDate) {

        List<BmProjectCost> list = new ArrayList<>();
        // 治工具费用-资本支出费用
        List<Map<String, Object>> toolCapitalData = restService.getToolCapitalFromBm(project, fromDate, toDate);
        toolCapitalData.forEach(map -> {
            int i= 0;
        });
        toolCapitalData.forEach(map -> {
            BmProjectCost bmProjectCost = new BmProjectCost(project, stage, CostTypeEnum.Tool.getCostType(), "资本支出");
            bmProjectCost.setFromDate(fromDate);
            bmProjectCost.setToDate(toDate);
            analyseBmProjectCost(bmProjectCost, map);
            list.add(bmProjectCost);
        });

        // 治工具费用-其他费用
        List<Map<String, Object>> toolOtherData = restService.getToolOtherFromBm(project, fromDate, toDate);
        toolOtherData.forEach(map -> {
            BmProjectCost bmProjectCost = new BmProjectCost(project, stage, CostTypeEnum.Tool.getCostType(), "其他费用");
            bmProjectCost.setFromDate(fromDate);
            bmProjectCost.setToDate(toDate);
            analyseBmProjectCost(bmProjectCost, map);
            list.add(bmProjectCost);
        });

        // 直接材料费用（外包薄化）
        List<Map<String, Object>> directMaterialData = restService.getDirectMaterialCost_thinFromBm(project, fromDate, toDate);
        directMaterialData.forEach(map -> {
            BmProjectCost bmProjectCost = new BmProjectCost(project, stage, CostTypeEnum.DirectMaterial.getCostType(), "其他费用");
            bmProjectCost.setFromDate(fromDate);
            bmProjectCost.setToDate(toDate);
            analyseBmProjectCost(bmProjectCost, map);
            list.add(bmProjectCost);
        });
        // 验证费用
        List<Map<String, Object>> validationData = restService.getValidationCostFromBm(project, fromDate, toDate);
        validationData.forEach(map -> {
            BmProjectCost bmProjectCost = new BmProjectCost(project, stage, CostTypeEnum.Validation.getCostType(), "其他费用");
            bmProjectCost.setFromDate(fromDate);
            bmProjectCost.setToDate(toDate);
            analyseBmProjectCost(bmProjectCost, map);
            list.add(bmProjectCost);
        });

        saveBmProjectCost(list);
        return list;
    }


    private void analyseBmProjectCost(BmProjectCost bmProjectCost, Map<String, Object> map) {
        bmProjectCost.setPrOrderNumber((String)map.get("TrackingNumber"));
        bmProjectCost.setAmount(((BigDecimal) map.get("amount")).doubleValue());
    }

    /**
     * 确定阶段的时间区间
     * @param project
     */
    private Map<String, Map<String, Date>> handleDateRange(String project) {
        PlmProjectSchedule designSchedule = scheduleService.getPlmSchedule(project, StageTypeEnum.DESIGN.getStage());
        Date designDate = designSchedule.getDate();

        Map<String, Map<String, Date>> dateRangeMap = new HashMap<>();
        List<String> stages = stageService.getStages(project);
        stages.remove(StageTypeEnum.NPRB.getStage());
        stages.remove(StageTypeEnum.DESIGN.getStage());

        Date fromDate = designDate;
        for(String stage : stages) {
            List<PlmProjectStage> plmStageList = stageService.getPlmStageList(project, stage);
            Date noteDate = plmStageList.get(0).getInDate();
            for(PlmProjectStage plmProjectStage : plmStageList) {
                if(noteDate.compareTo(plmProjectStage.getInDate()) < 0) {
                    noteDate = plmProjectStage.getInDate();
                }
            }

            Map<String, Date> map = new HashMap<>();
            map.put("fromDate", fromDate);
            map.put("toDate", noteDate);
            dateRangeMap.put(stage, map);
            fromDate =  noteDate;
        }

        return dateRangeMap;
    }

    private List<BmProjectCost> getBmProjectCostList(String project, String stage, String costType) {
        return repository.findByProjectAndStageAndCostType(project, stage, costType);
    }

    private List<BmProjectCost> getBmProjectCostList(String project) {
        return repository.findByProject(project);
    }

    private void saveBmProjectCost(List<BmProjectCost> list) {
        repository.saveAll(list);
    }

    private void deleteBmProjectCost(List<BmProjectCost> list) {
        repository.deleteAll(list);
    }

    @Override
    public List<BmProjectCost> getBmProjectCost(String project) {
        return repository.findByProject(project);
    }
}
