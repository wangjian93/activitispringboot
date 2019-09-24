package com.ivo.modules.coq.cost2.impl;

import com.ivo.modules.coq.cost.DoubleUtil;
import com.ivo.modules.coq.cost2.StageCostMethod;
import com.ivo.modules.coq.domain.*;
import com.ivo.modules.coq.enums.FactoryTypeEnum;
import com.ivo.modules.coq.enums.StageTypeEnum;
import com.ivo.modules.coq.service.BmProjectCostService;
import com.ivo.modules.coq.service.EEProjectCostService;
import com.ivo.modules.coq.service.PlmProjectMemberService;
import com.ivo.modules.coq.service.PlmProjectStageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: wj
 * @Date: 2019-08-30 15:03
 * @Version 1.0
 */
public abstract class AbstractStageCostMethod implements StageCostMethod {

    @Autowired
    private PlmProjectStageService stageService;

    @Autowired
    private EEProjectCostService eeService;

    @Autowired
    private BmProjectCostService bmService;

    @Autowired
    private PlmProjectMemberService memberService;

    @Override
    public void getDirectMaterialCost(ProjectStageCost2 projectStageCost) {
        if(StringUtils.containsAny(projectStageCost.getStage(), StageTypeEnum.NPRB.getStage(), StageTypeEnum.DESIGN.getStage())) {
            return;
        }

        String project = projectStageCost.getProjectCost().getProject();
        String stage = projectStageCost.getStage();
        Double amount = null;
        // 直接材料
        List<PlmProjectStage> stageList = stageService.getPlmStageList(project, stage);
        for(PlmProjectStage s : stageList) {
            EEProjectCost eeProjectCost = eeService.getEEProject(project, stage, s.getProcess());
            if(eeProjectCost == null) {
                continue;
            }

            Double directCost = null;
            String factory = eeProjectCost.getFactory();
            if(StringUtils.equalsIgnoreCase(FactoryTypeEnum.ARRAY.getFactory(), factory)) {
                directCost = DoubleUtil.multiply(eeProjectCost.getMaterialPrice() * s.getInQuantity());

            } else if(StringUtils.equalsIgnoreCase(FactoryTypeEnum.CELL.getFactory(), factory)) {
                directCost = DoubleUtil.multiply(eeProjectCost.getMaterialPrice() * s.getInQuantity());

            } else if(StringUtils.equalsIgnoreCase(FactoryTypeEnum.MODULE.getFactory(), factory)) {
                directCost = eeProjectCost.getWoCost();
            }

            DirectMaterialCostDetail directMaterialCostDetail = new DirectMaterialCostDetail();
            directMaterialCostDetail.setProjectStageCost(projectStageCost);
            directMaterialCostDetail.setProcess(s.getProcess());
            directMaterialCostDetail.setCost(directCost);
            directMaterialCostDetail.setType(DirectMaterialCostDetail.TYPE_Direct);
            projectStageCost.getDirectMaterialCostDetailList().add(directMaterialCostDetail);

            amount = DoubleUtil.sum(amount, directCost);
        }

        // 外包薄化
        Double directCost_thin =  bmService.getDirectMaterialCost_thin(project, stage);
        DirectMaterialCostDetail directMaterialCostDetail = new DirectMaterialCostDetail();
        directMaterialCostDetail.setProjectStageCost(projectStageCost);
        directMaterialCostDetail.setCost(directCost_thin);
        directMaterialCostDetail.setType(DirectMaterialCostDetail.TYPE_THIN);
        projectStageCost.getDirectMaterialCostDetailList().add(directMaterialCostDetail);

        amount = DoubleUtil.sum(amount, directCost_thin);
        projectStageCost.setDirectMaterialCost(amount);
    }

    @Override
    public void getToolCost(ProjectStageCost2 projectStageCost) {
        if(StringUtils.containsAny(projectStageCost.getStage(), StageTypeEnum.NPRB.getStage(), StageTypeEnum.DESIGN.getStage())) {
            return;
        }

            String project = projectStageCost.getProjectCost().getProject();
        String stage = projectStageCost.getStage();
        Double toolCost = bmService.getToolCostFromBm(project, stage);
        projectStageCost.setToolCost(toolCost);
    }

    @Override
    public void getValidationCost(ProjectStageCost2 projectStageCost) {
        if(StringUtils.containsAny(projectStageCost.getStage(), StageTypeEnum.NPRB.getStage(), StageTypeEnum.DESIGN.getStage())) {
            return;
        }

        String project = projectStageCost.getProjectCost().getProject();
        String stage = projectStageCost.getStage();
        // BM验证费用部分
        Double validationCost = bmService.getValidationCost(project, stage);

        // 厂内验证费用部分

        projectStageCost.setValidationCost(validationCost);
    }

    @Override
    public void getProductionCost(ProjectStageCost2 projectStageCost) {
        if(StringUtils.containsAny(projectStageCost.getStage(), StageTypeEnum.NPRB.getStage(), StageTypeEnum.DESIGN.getStage())) {
            return;
        }

    }

    @Override
    public void getReworkAndScrapCost(ProjectStageCost2 projectStageCost) {
        if(StringUtils.containsAny(projectStageCost.getStage(), StageTypeEnum.NPRB.getStage(), StageTypeEnum.DESIGN.getStage())) {
            return;
        }


    }

    @Override
    public void getSalaryCost(ProjectStageCost2 projectStageCost) {
        if(!StringUtils.containsAny(projectStageCost.getStage(), StageTypeEnum.NPRB.getStage(), StageTypeEnum.DESIGN.getStage())) {
            return;
        }

        Double salaryCost = null;
        if(StringUtils.containsIgnoreCase(projectStageCost.getStage(), StageTypeEnum.NPRB.getStage())) {
            List<PlmProjectMember> memberList = memberService.getPlmMemberList(projectStageCost.getProjectCost().getProject());
            int workDays = 0;
            for(PlmProjectMember member : memberList) {
                // 6工作日：PM、PJM、LCD RD
                if(StringUtils.equalsAnyIgnoreCase(member.getRole(), "PM", "PJM", "LCD", "RD")) {
                    workDays += 6;
                }
                // 3工作日：EE RD、ME RD 、RD-Packing 、NPE-Cell 、NPE-Array 、NPE-Lcm
                if(StringUtils.equalsAnyIgnoreCase(member.getRole(), "EE RD", "ME RD", "RD-Packing", "NPE-Cell", "NPE-Array", "NPE-Lcm")) {
                    workDays += 3;
                }
                // 5工作日：LCM TEC
                if(StringUtils.equalsAnyIgnoreCase(member.getRole(), "LCM TEC")) {
                    workDays += 5;
                }
            }

            // 基本薪资按6000计算，月平均工作日为21.75
            Double baseSalary = 6000D;
            salaryCost = baseSalary/21.75*workDays;
        }

        projectStageCost.setSalaryCost(salaryCost);
    }

    @Override
    public void getRmaCost(ProjectStageCost2 projectStageCost) {
        if(StringUtils.containsAny(projectStageCost.getStage(), StageTypeEnum.NPRB.getStage(), StageTypeEnum.DESIGN.getStage())) {
            return;
        }
    }

    @Override
    public void getObaCost(ProjectStageCost2 projectStageCost) {
        if(StringUtils.containsAny(projectStageCost.getStage(), StageTypeEnum.NPRB.getStage(), StageTypeEnum.DESIGN.getStage())) {
            return;
        }
    }

    @Override
    public void getTravelCost(ProjectStageCost2 projectStageCost) {
        if(!StringUtils.containsAny(projectStageCost.getStage(), StageTypeEnum.NPRB.getStage(), StageTypeEnum.DESIGN.getStage())) {
            return;
        }
    }
}
