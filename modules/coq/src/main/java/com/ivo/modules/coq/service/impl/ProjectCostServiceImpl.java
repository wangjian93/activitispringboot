package com.ivo.modules.coq.service.impl;

import com.ivo.common.enums.StatusEnum;
import com.ivo.modules.coq.cost.formula.CostService;
import com.ivo.modules.coq.cost.formula.ProjectCostFormula;
import com.ivo.modules.coq.cost.formula.StageCostFormula;
import com.ivo.modules.coq.domain.*;
import com.ivo.modules.coq.repository.ProjectCostRepository;
import com.ivo.modules.coq.rest.RestService;
import com.ivo.modules.coq.service.ProjectCostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: wj
 * @Date: 2019-06-24 15:03
 * @Version 1.0
 */
@Service
@Slf4j
public class ProjectCostServiceImpl implements ProjectCostService {

    @Autowired
    private ProjectCostRepository repository;

    @Autowired
    private RestService restService;

    @Autowired
    private CostService costService;

    /**
     * 获取机种的成本数据
     * @param projectName
     * @return
     */
    @Override
    public ProjectCost getProjectCost(String projectName) {
        ProjectCost projectCost = repository.findDistinctByProjectName(projectName);

        // 生成或更新
        if(projectCost == null) {
            projectCost = generateProjectCost(projectName);
        } else {
            projectCost = updateProjectCost(projectName);
        }
        return projectCost;
    }

    /**
     * 生成机种成本数据
     * @param projectName
     * @return
     */
    @Override
    public ProjectCost generateProjectCost(String projectName) {
        ProjectCost projectCost = new ProjectCost(projectName);
        generateOrUpdateProjectCost(projectCost);
        return projectCost;
    }

    /**
     * 更新机种成本数据
     * @param projectName
     * @return
     */
    @Override
    public ProjectCost updateProjectCost(String projectName) {
        ProjectCost projectCost = repository.findDistinctByProjectName(projectName);
        generateOrUpdateProjectCost(projectCost);
        return projectCost;
    }

    /**
     * 持久化机种成本数据
     * @param projectCost
     */
    @Override
    public void saveProjectCost(ProjectCost projectCost) {
//        repository.save(projectCost);
    }

    /**
     * 生成或更新机种的成本数据
     * @param projectCost
     * @return
     */
    @Override
    public void generateOrUpdateProjectCost(ProjectCost projectCost) {
        //生成或更新阶段信息
        setStage(projectCost);

        //生成或更新阶段的成本信息
        setStageCost(projectCost);

        //生成或更新机种的成本信息，汇总的数据
        setProjectCost(projectCost);

        //持久化
        saveProjectCost(projectCost);
    }

    /**
     * 生成机种的阶段信息
     * @param projectCost
     */
    @Override
    public void setStage(ProjectCost projectCost) {
        // 通过接口获取机种的阶段信息
        List<Map<String, String>> restList = restService.getProjectStage(projectCost.getProjectName());

        Map<String, ProjectStageCost> restMap = new HashMap<>();
        restList.forEach(map -> {
            // 解析接口返回的数据
            String stage = map.get("Stage");
            String process = map.get("Process");
            Double inQuantity = Double.valueOf(map.get("inQuantity"));
            Double outQuantity = Double.valueOf(map.get("outQuantity"));
            boolean complete = Boolean.valueOf(map.get("complete"));
            String orderNumber = map.get("orderNumber");
            String units = map.get("units");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date inDate = null;
            Date outDate = null;
            try {
                inDate = format.parse(String.valueOf(map.get("inDate")));
                outDate = format.parse(String.valueOf(map.get("outDate")));
            } catch (ParseException e) {
                log.error(e.getMessage());
            }

            ProjectStageCost projectStageCost = restMap.get(stage);
            if(projectStageCost == null) {
                projectStageCost = new ProjectStageCost(stage, projectCost);
                restMap.put(stage, projectStageCost);
            }

            StageDetail stageDetail = new StageDetail(projectStageCost);
            stageDetail.setProcess(process);
            stageDetail.setInDate(inDate);
            stageDetail.setOutDate(outDate);
            stageDetail.setInQuantity(inQuantity);
            stageDetail.setOutQuantity(outQuantity);
            stageDetail.setComplete(complete);
            stageDetail.setOrderNumber(orderNumber);
            stageDetail.setUnits(units);

            projectStageCost.getStageDetails().add(stageDetail);
        });

        List<ProjectStageCost> projectStageCosts = projectCost.getProjectStageCosts();
        //更新或删除projectCost的projectStageCost
        projectStageCosts.forEach(projectStageCost -> {
            ProjectStageCost restObject = restMap.get(projectStageCost.getStage());
            if(restObject == null) {
                // 删除
                projectStageCost.setValidFlag(StatusEnum.DELETE.getCode());
                projectStageCost.getStageDetails().forEach(stageDetail -> {
                    stageDetail.setValidFlag(StatusEnum.DELETE.getCode());
                });
            } else {
                // 更新
                List<StageDetail> restStgDetail = restObject.getStageDetails();
                restStgDetail.forEach(stageDetail -> {
                    stageDetail.setProjectStageCost(projectStageCost);
                });

                projectStageCost.getStageDetails().forEach(stageDetail -> {
                    stageDetail.setValidFlag(StatusEnum.DELETE.getCode());
                    projectStageCost.getStageDetails().remove(stageDetail);
                });
                projectStageCost.getStageDetails().addAll(restStgDetail);
            }

            restMap.remove(projectStageCost.getStage());
        });

        //添加
        restMap.forEach((key, restObject) -> {
            restObject.setProjectCost(projectCost);
            projectCost.getProjectStageCosts().add(restObject);
        });
    }

    /**
     * 生成机种阶段成本信息
     * @param projectCost
     */
    @Override
    public void setStageCost(ProjectCost projectCost) {
        projectCost.getProjectStageCosts().forEach(projectStageCost -> {

            StageCostFormula formula = costService.getStageCostFormula(projectStageCost.getStage());

            // 直接材料成本
            projectStageCost.setDirectMaterialCost(
                    formula.getDirectMaterialCost(projectCost.getProjectName(), projectStageCost.getStage(), projectStageCost)
            );
            // 治工具成本
            projectStageCost.setToolCost(
                    formula.getToolCost(projectCost.getProjectName(), projectStageCost.getStage(), projectStageCost)
            );
            // 验证费用
            projectStageCost.setValidationCost(
                    formula.getValidationCost(projectCost.getProjectName(), projectStageCost.getStage(), projectStageCost)
            );
            // 生产费用
            projectStageCost.setProductionCost(
                    formula.getProductionCost(projectCost.getProjectName(), projectStageCost.getStage(), projectStageCost)
            );
            // 重工/报废费用
            projectStageCost.setReworkAndScrapCost(
                    formula.getReworkAndScrapCost(projectCost.getProjectName(), projectStageCost.getStage(), projectStageCost)
            );
            // 研发人员薪资费用
            projectStageCost.setSalaryCost(
                    formula.getSalaryCost(projectCost.getProjectName(), projectStageCost.getStage(), projectStageCost)
            );
            // RMA成本
            projectStageCost.setRmaCost(
                    formula.getRmaCost(projectCost.getProjectName(), projectStageCost.getStage(), projectStageCost)
            );
            // OBA成本
            projectStageCost.setObaCost(
                    formula.getObaCost(projectCost.getProjectName(), projectStageCost.getStage(), projectStageCost)
            );
            // 差旅费用
            projectStageCost.setTravelCost(
                    formula.getTravelCost(projectCost.getProjectName(), projectStageCost.getStage(), projectStageCost)
            );


            // 预防成本
            projectStageCost.setPrecautionCost(
                    formula.computePrecautionCost(projectStageCost)
            );
            // 鉴定成本
            projectStageCost.setIdentifyCost(
                    formula.computeIdentifyCost(projectStageCost)
            );
            // 内损成本
            projectStageCost.setInLossCost(
                    formula.computeInLossCost(projectStageCost)
            );
            // 外损成本
            projectStageCost.setOutLossCost(
                    formula.computeOutLossCost(projectStageCost)
            );
        });
    }

    /**
     * 生成机种的成本信息，成本的汇总
     * @param projectCost
     */
    @Override
    public void setProjectCost(ProjectCost projectCost) {

        ProjectCostFormula formula = costService.getProjectCostFormula();

        // 必要花费
        projectCost.setNecessaryCost(
                formula.computeNecessaryCost(projectCost)
        );
        // 多余花费
        projectCost.setUnnecessaryCost(
                formula.computeUnnecessaryCost(projectCost)
        );
        // 预防成本
        projectCost.setPrecautionCost(
                formula.computePrecautionCost(projectCost)
        );
        // 鉴定成本
        projectCost.setIdentifyCost(
                formula.computeIdentifyCost(projectCost)
        );
        // 内损成本
        projectCost.setInLossCost(
                formula.computeInLossCost(projectCost)
        );
        // 外损成本
        projectCost.setOutLossCost(
                formula.computeOutLossCost(projectCost)
        );

    }
}
