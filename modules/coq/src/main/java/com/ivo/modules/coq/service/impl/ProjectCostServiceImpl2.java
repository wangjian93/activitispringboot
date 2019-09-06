package com.ivo.modules.coq.service.impl;

import com.ivo.modules.coq.cost2.CostMethod;
import com.ivo.modules.coq.cost2.SecondarySubjectMethod;
import com.ivo.modules.coq.cost2.StageCostMethod;
import com.ivo.modules.coq.cost2.impl.*;
import com.ivo.modules.coq.domain.*;
import com.ivo.modules.coq.enums.StageTypeEnum;
import com.ivo.modules.coq.repository.ProjectCostRepository2;
import com.ivo.modules.coq.service.PlmProjectStageService;
import com.ivo.modules.coq.service.ProjectCostService2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wj
 * @Date: 2019-08-30 14:37
 * @Version 1.0
 */
@Service
public class ProjectCostServiceImpl2 implements ProjectCostService2 {

    @Autowired
    private ProjectCostRepository2 repository;

    @Autowired
    private PlmProjectStageService stageService;

    @Override
    public ProjectCost2 getProject(String project) {
        return repository.findDistinctByProject(project);
    }

    @Autowired
    private NprbStageCostMethod nprbStageCostMethod;
    @Autowired
    private DesignStageCostMethod designStageCostMethod;
    @Autowired
    private EvtStageCostMethod evtStageCostMethod;
    @Autowired
    private DvtStageCostMethod dvtStageCostMethod;
    @Autowired
    private PvtStageCostMethod pvtStageCostMethod;

    @Autowired
    private NprbSecondarySubjectMethod nprbSecondarySubjectMethod;
    @Autowired
    private DesignSecondarySubjectMethod  designSecondarySubjectMethod;
    @Autowired
    private EvtSecondarySubjectMethod evtSecondarySubjectMethod;
    @Autowired
    private DvtSecondarySubjectMethod dvtSecondarySubjectMethod;
    @Autowired
    private PvtSecondarySubjectMethod pvtSecondarySubjectMethod;

    @Autowired
    private CostMethod costMethod;

    private StageCostMethod getStageCostMethod(String stage) {
        if(StringUtils.containsIgnoreCase(stage, StageTypeEnum.NPRB.getStage())) {
            return nprbStageCostMethod;
        } else
        if(StringUtils.containsIgnoreCase(stage, StageTypeEnum.DESIGN.getStage())) {
            return designStageCostMethod;
        } else
        if(StringUtils.containsIgnoreCase(stage, StageTypeEnum.EVT.getStage())) {
            return evtStageCostMethod;
        } else
        if(StringUtils.containsIgnoreCase(stage, StageTypeEnum.DVT.getStage())) {
            return dvtStageCostMethod;
        } else
        if(StringUtils.containsIgnoreCase(stage, StageTypeEnum.PVT.getStage())) {
            return pvtStageCostMethod;
        } else
        return null;
    }

    private SecondarySubjectMethod getSecondarySubjectMethod(String stage) {
        if(StringUtils.containsIgnoreCase(stage, StageTypeEnum.NPRB.getStage())) {
            return nprbSecondarySubjectMethod;
        }
        if(StringUtils.containsIgnoreCase(stage, StageTypeEnum.DESIGN.getStage())) {
            return designSecondarySubjectMethod;
        }
        if(StringUtils.containsIgnoreCase(stage, StageTypeEnum.EVT.getStage())) {
            return evtSecondarySubjectMethod;
        }
        if(StringUtils.containsIgnoreCase(stage, StageTypeEnum.DVT.getStage())) {
            return dvtSecondarySubjectMethod;
        }
        if(StringUtils.containsIgnoreCase(stage, StageTypeEnum.PVT.getStage())) {
            return pvtSecondarySubjectMethod;
        }
        return null;
    }

    @Override
    public ProjectCost2 syncScheduleFromPlm(String project) {
        // 删除原来数据
        deleteProjectCost(getProject(project));

        // 同步新数据
        List<String> stageList = stageService.getStages(project);

        // 机种阶段
        ProjectCost2 projectCost = new ProjectCost2(project);
        stageList.forEach(stage -> {
            ProjectStageCost2 projectStageCost = new ProjectStageCost2(projectCost, stage);
            projectCost.getProjectStageCostList().add(projectStageCost);
        });

        // 机种二级科目
        projectCost.getProjectSecondarySubjectCostList().add(
                new ProjectSecondarySubjectCost2(projectCost, StageTypeEnum.NPRB.getStage())
        );
        projectCost.getProjectSecondarySubjectCostList().add(
                new ProjectSecondarySubjectCost2(projectCost, StageTypeEnum.DESIGN.getStage())
        );
        projectCost.getProjectSecondarySubjectCostList().add(
                new ProjectSecondarySubjectCost2(projectCost, StageTypeEnum.EVT.getStage())
        );
        projectCost.getProjectSecondarySubjectCostList().add(
                new ProjectSecondarySubjectCost2(projectCost, StageTypeEnum.DVT.getStage())
        );
        projectCost.getProjectSecondarySubjectCostList().add(
                new ProjectSecondarySubjectCost2(projectCost, StageTypeEnum.PVT.getStage())
        );

        // 计算各阶段成本
        projectCost.getProjectStageCostList().forEach(projectStageCost -> {
            StageCostMethod stageCostMethod = getStageCostMethod(projectStageCost.getStage());
            // 直接材料成本
            stageCostMethod.getDirectMaterialCost(projectStageCost);
            // 治工具成本
            stageCostMethod.getToolCost(projectStageCost);
            // 验证费用
            stageCostMethod.getValidationCost(projectStageCost);
            // 生产费用
            stageCostMethod.getProductionCost(projectStageCost);
            // 重工/报废费用
            stageCostMethod.getReworkAndScrapCost(projectStageCost);
            // 研发人员薪资费用
            stageCostMethod.getSalaryCost(projectStageCost);
            // RMA成本
            stageCostMethod.getRmaCost(projectStageCost);
            // OBA成本
            stageCostMethod.getObaCost(projectStageCost);
            // 差旅费用
            stageCostMethod.getTravelCost(projectStageCost);
        });

        // 计算二级科目成本
        projectCost.getProjectSecondarySubjectCostList().forEach(projectSecondarySubjectCost -> {
            SecondarySubjectMethod secondarySubjectMethod = getSecondarySubjectMethod(projectSecondarySubjectCost.getStage());
            secondarySubjectMethod.setPrecautionCost(projectSecondarySubjectCost, projectCost.getProjectStageCostList());
            secondarySubjectMethod.setIdentifyCost(projectSecondarySubjectCost, projectCost.getProjectStageCostList());
            secondarySubjectMethod.setInLossCost(projectSecondarySubjectCost, projectCost.getProjectStageCostList());
            secondarySubjectMethod.setOutLossCost(projectSecondarySubjectCost, projectCost.getProjectStageCostList());
        });

        // 汇总
        costMethod.getPrecautionCost(projectCost);
        costMethod.getIdentifyCost(projectCost);
        costMethod.getInLossCost(projectCost);
        costMethod.getOutLossCost(projectCost);
        costMethod.getNecessaryCost(projectCost);
        costMethod.getUnnecessaryCost(projectCost);

        saveProjectCost(projectCost);
        return getProject(project);
    }

    private void deleteProjectCost(ProjectCost2 projectCost) {
        if(projectCost == null) {
            return;
        }
        repository.delete(projectCost);
    }

    private void saveProjectCost(ProjectCost2 projectCost) {
        repository.save(projectCost);
    }
}
