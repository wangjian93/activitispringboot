package com.ivo.modules.coq.cost.formula;

import com.ivo.modules.coq.cost.DoubleUtil;
import com.ivo.modules.coq.domain.*;
import com.ivo.modules.coq.enums.StageTypeEnum;
import com.ivo.modules.coq.service.BmProjectCostService;
import com.ivo.modules.coq.service.BmService;
import com.ivo.modules.coq.service.MaterialPriceService;
import com.ivo.modules.eifapi.service.BmCostService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jolokia.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import sun.java2d.pipe.AAShapePipe;

import java.util.Date;

/**
 * 获取机种阶段成本的抽象类
 * @Author: wj
 * @Date: 2019-07-29 11:29
 * @Version 1.0
 */
@Slf4j
public abstract class AbstractStageCostFormula implements StageCostFormula {

    @Autowired
    private MaterialPriceService materialPriceService;

    @Autowired
    private BmService bmService;

    @Autowired
    private BmProjectCostService bmProjectCostService;

    /**
     * 获取直接材料成本
     * @param projectName 机种
     * @param stage 阶段
     * @return
     */
    @Override
    public Double getDirectMaterialCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        //TODO... 阶段直接材料成本接口
        Double count = null;
        for(StageDetail stageDetail : projectStageCost.getStageDetails()) {
            log.info("------计算[" + stageDetail.getProcess() + "] Process直接材料成本");
            if(StringUtils.isEmpty(stageDetail.getOrderNumber())) {
                log.warn("[" + stageDetail.getProcess() + "] Process单号不存在");
                continue;
            }

            MaterialPrice materialPrice =  materialPriceService.getMaterialPrice(stageDetail.getOrderNumber());

            Double cost = null;

            if(materialPrice.getPlant().equals("Array")) {
                cost = DoubleUtil.multiply(stageDetail.getInQuantity(), materialPrice.getPrice());
            } else if(materialPrice.getPlant().equals("Cell")) {
                cost = DoubleUtil.multiply(stageDetail.getInQuantity(), materialPrice.getPrice());
            } else if(materialPrice.getPlant().equals("Module")) {
                cost = materialPrice.getWoCost();
            }

            if(cost == null) {
                log.warn("[" + stageDetail.getProcess() + "] Process直接材料成本计算为空");
            }
            count = DoubleUtil.sum(cost, count);
        }
        return count;
    }

    /**
     * 获取治工具成本
     * @param projectName 机种
     * @param stage 阶段
     * @return
     */
    @Override
    public Double getToolCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        //TODO... 阶段治工具成本接口
        if(StringUtils.equalsIgnoreCase(stage, StageTypeEnum.NPRB.getStage()) ||
        StringUtils.equalsIgnoreCase(stage, StageTypeEnum.DESIGN.getStage())) {
            return null;
        }
        return bmProjectCostService.getToolCostFromBm(projectName, stage);
    }

    /**
     * 获取验证费用
     * @param projectName 机种
     * @param stage 阶段
     * @return
     */
    @Override
    public Double getValidationCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        //TODO... 阶段验证费用成本接口

        if(StringUtils.equalsIgnoreCase(stage, StageTypeEnum.NPRB.getStage()) ||
                StringUtils.equalsIgnoreCase(stage, StageTypeEnum.DESIGN.getStage())) {
            return null;
        }
        return bmProjectCostService.getValidationCost(projectName, stage);
    }

    /**
     * 获取生产费用
     * @param projectName 机种
     * @param stage 阶段
     * @return
     */
    @Override
    public Double getProductionCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        //TODO... 阶段生产费用成本接口
        return null;
    }

    /**
     * 获取重工/报废费用
     * @param projectName 机种
     * @param stage 阶段
     * @return
     */
    @Override
    public Double getReworkAndScrapCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        //TODO... 阶段重工报废费用成本接口
        return null;
    }

    /**
     * 获取研发人员薪资
     * @param projectName
     * @param stage
     * @return
     */
    @Override
    public Double getSalaryCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        //TODO... 阶段研发人员薪资成本接口
        return null;
    }

    /**
     * 获取RMA成本
     * @param projectName 机种
     * @param stage 阶段
     * @return
     */
    @Override
    public Double getRmaCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        //TODO... 阶段RMA成本接口
        return null;
    }

    /**
     * 获取OBA成本
     * @param projectName
     * @param stage
     * @return
     */
    @Override
    public Double getObaCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        //TODO... 阶段OBA成本接口
        return null;
    }

    /**
     * 获取差旅费用
     * @param projectName 机种
     * @param stage 阶段
     * @return
     */
    @Override
    public Double getTravelCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        //TODO... 阶段差率费成本接口
        return null;
    }

    /**
     * 计算预防成本
     * @param projectStageCost
     * @return
     */
    @Override
    public Double computePrecautionCost(ProjectStageCost projectStageCost) {
        //TODO... 阶段预防成本计算
        return null;
    }

    /**
     * 计算鉴定成本
     * @param projectStageCost
     * @return
     */
    @Override
    public Double computeIdentifyCost(ProjectStageCost projectStageCost) {
        //TODO... 阶段鉴定成本计算
        return null;
    }

    /**
     * 计算内损成本
     * @param projectStageCost
     * @return
     */
    @Override
    public Double computeInLossCost(ProjectStageCost projectStageCost) {
        //TODO... 阶段内损成本计算
        return null;
    }

    /**
     * 计算外损成本
     * @param projectStageCost
     * @return
     */
    @Override
    public Double computeOutLossCost(ProjectStageCost projectStageCost) {
        //TODO... 阶段外损成本计算
        return null;
    }

}
