package com.ivo.modules.coq.service.impl;

import com.ivo.common.enums.StatusEnum;
import com.ivo.modules.coq.cost.formula.CostService;
import com.ivo.modules.coq.domain.*;
import com.ivo.modules.coq.enums.StageTypeEnum;
import com.ivo.modules.coq.repository.ProjectCostRepository;
import com.ivo.modules.coq.rest.RestService;
import com.ivo.modules.coq.service.ProjectCostService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
        log.info("获取机种[" + projectName + "]的成本数据");
        ProjectCost projectCost = repository.findDistinctByProjectName(projectName);

        // 不存在时生成
        if(projectCost == null) {
            log.info("机种[" + projectName + "]的成本数据系统不能存在开始生成");
            projectCost = generateProjectCost(projectName);
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
        log.info("生成机种[" + projectName + "]的成本数据 start...");
        ProjectCost projectCost = new ProjectCost(projectName);
        generateOrUpdateProjectCost(projectCost);
        log.info("生成机种[" + projectName + "]的成本数据成功 end.");
        return projectCost;
    }

    /**
     * 更新机种成本数据
     * @param projectName
     * @return
     */
    @Override
    public ProjectCost updateProjectCost(String projectName) {
        log.info("更新机种[" + projectName + "]的成本数据 start...");
        ProjectCost projectCost = repository.findDistinctByProjectName(projectName);
        generateOrUpdateProjectCost(projectCost);
        log.info("更新机种[" + projectName + "]的成本数据成功 end");
        return projectCost;
    }

    /**
     * 持久化机种成本数据
     * @param projectCost
     */
    @Override
    public void saveProjectCost(ProjectCost projectCost) {
        repository.save(projectCost);
    }

    /**
     * 生成或更新机种的成本数据
     * @param projectCost
     * @return
     */
    @Override
    public void generateOrUpdateProjectCost(ProjectCost projectCost) {
        //生成或更新阶段信息
        log.info("--1.获取机种[" + projectCost.getProjectName() + "]的阶段信息");
        setStage(projectCost);

        //生成或更新阶段的成本信息
        log.info("--2.计算机种[" + projectCost.getProjectName() + "]各个阶段的成本");
        setStageCost(projectCost);

        //生成或更新机种的成本信息，汇总的数据
        log.info("--3.计算机种[" + projectCost.getProjectName() + "]的成本汇总");
        setProjectCost(projectCost);

        //对机种的阶段先后顺序进行排序
        projectCost.getProjectStageCosts().sort((ProjectStageCost o1, ProjectStageCost o2) -> {
            // 阶段截取掉"-"及后内容
            String stage1 = StringUtils.substringBefore(o1.getStage(), "-");
            String stage2 = StringUtils.substringBefore(o2.getStage(), "-");
            //阶段的先后顺序
            Map<String, Integer> map = new HashMap<>();
            map.put(StageTypeEnum.NPRB.getStage(), 1);
            map.put(StageTypeEnum.DESIGN.getStage(), 2);
            map.put(StageTypeEnum.EVT.getStage(), 3);
            map.put(StageTypeEnum.DVT.getStage(), 4);
            map.put(StageTypeEnum.PVT.getStage(), 5);

            if(map.get(stage1) == null || map.get(stage2) == null) {
                return 0;
            } else {
                return map.get(stage1) - map.get(stage2);
            }
        });

        //持久化
        log.info("--4.持久化保存机种[" + projectCost.getProjectName() + "]的成本数据");
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

        Map<String, ProjectStageCost> restMap = new LinkedHashMap<>();
        restList.forEach(map -> {
            // 解析接口返回的数据
            // 阶段全部大写
            String stage = map.get("Stage").toUpperCase();
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

        // 补上Design、NPRB阶段
        if(restMap.get(StageTypeEnum.NPRB.getStage()) == null){
            ProjectStageCost projectStageCost = new ProjectStageCost(StageTypeEnum.NPRB.getStage(), projectCost);
            projectStageCost.getStageDetails().add(new StageDetail());
            restMap.put(StageTypeEnum.NPRB.getStage(), projectStageCost);
        }
        if(restMap.get(StageTypeEnum.DESIGN.getStage()) == null){
            ProjectStageCost projectStageCost = new ProjectStageCost(StageTypeEnum.DESIGN.getStage(), projectCost);
            projectStageCost.getStageDetails().add(new StageDetail());
            restMap.put(StageTypeEnum.DESIGN.getStage(), projectStageCost);
        }

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

            // 直接材料成本
            log.info("----计算[" + projectStageCost.getStage() + "]阶段的直接材料成本");
            projectStageCost.setDirectMaterialCost(
                    costService.getDirectMaterialCost(projectCost.getProjectName(), projectStageCost.getStage(), projectStageCost)
            );
            // 治工具成本
            log.info("----计算[" + projectStageCost.getStage() + "]阶段的治工具成本");
            projectStageCost.setToolCost(
                    costService.getToolCost(projectCost.getProjectName(), projectStageCost.getStage(), projectStageCost)
            );
            // 验证费用
            log.info("----计算[" + projectStageCost.getStage() + "]阶段的验证费用");
            projectStageCost.setValidationCost(
                    costService.getValidationCost(projectCost.getProjectName(), projectStageCost.getStage(), projectStageCost)
            );
            // 生产费用
            log.info("---计算[" + projectStageCost.getStage() + "]阶段的生产费用");
            projectStageCost.setProductionCost(
                    costService.getProductionCost(projectCost.getProjectName(), projectStageCost.getStage(), projectStageCost)
            );
            // 重工/报废费用
            log.info("----计算[" + projectStageCost.getStage() + "]阶段的重工/报废费用");
            projectStageCost.setReworkAndScrapCost(
                    costService.getReworkAndScrapCost(projectCost.getProjectName(), projectStageCost.getStage(), projectStageCost)
            );
            // 研发人员薪资费用
            log.info("----计算[" + projectStageCost.getStage() + "]阶段的研发人员薪资费用");
            projectStageCost.setSalaryCost(
                    costService.getSalaryCost(projectCost.getProjectName(), projectStageCost.getStage(), projectStageCost)
            );
            // RMA成本
            log.info("----计算[" + projectStageCost.getStage() + "]阶段的RMA成本");
            projectStageCost.setRmaCost(
                    costService.getRmaCost(projectCost.getProjectName(), projectStageCost.getStage(), projectStageCost)
            );
            // OBA成本
            log.info("----计算[" + projectStageCost.getStage() + "]阶段的OBA成本");
            projectStageCost.setObaCost(
                    costService.getObaCost(projectCost.getProjectName(), projectStageCost.getStage(), projectStageCost)
            );
            // 差旅费用
            log.info("----计算[" + projectStageCost.getStage() + "]阶段的差旅费用");
            projectStageCost.setTravelCost(
                    costService.getTravelCost(projectCost.getProjectName(), projectStageCost.getStage(), projectStageCost)
            );
            // 预防成本
            log.info("----计算[" + projectStageCost.getStage() + "]阶段的预防成本");
            projectStageCost.setPrecautionCost(
                    costService.computePrecautionCost(projectStageCost)
            );
            // 鉴定成本
            log.info("----计算[" + projectStageCost.getStage() + "]阶段的鉴定成本");
            projectStageCost.setIdentifyCost(
                    costService.computeIdentifyCost(projectStageCost)
            );
            // 内损成本
            log.info("----计算[" + projectStageCost.getStage() + "]阶段的内损成本");
            projectStageCost.setInLossCost(
                    costService.computeInLossCost(projectStageCost)
            );
            // 外损成本
            log.info("----计算[" + projectStageCost.getStage() + "]阶段的外损成本");
            projectStageCost.setOutLossCost(
                    costService.computeOutLossCost(projectStageCost)
            );
        });
    }

    /**
     * 生成机种的成本信息，成本的汇总
     * @param projectCost
     */
    @Override
    public void setProjectCost(ProjectCost projectCost) {
        // 预防成本
        log.info("----计算机种[" + projectCost.getProjectName() + "]的预防成本");
        projectCost.setPrecautionCost(
                costService.summaryPrecautionCost(projectCost)
        );
        // 鉴定成本
        log.info("----计算机种[" + projectCost.getProjectName() + "]的鉴定成本");
        projectCost.setIdentifyCost(
                costService.summaryIdentifyCost(projectCost)
        );
        // 内损成本
        log.info("----计算机种[" + projectCost.getProjectName() + "]的内损成本");
        projectCost.setInLossCost(
                costService.summaryInLossCost(projectCost)
        );
        // 外损成本
        log.info("----计算机种[" + projectCost.getProjectName() + "]的外损成本");
        projectCost.setOutLossCost(
                costService.summaryOutLossCost(projectCost)
        );
        // 必要花费
        log.info("----计算机种[" + projectCost.getProjectName() + "]的必要花费");
        projectCost.setNecessaryCost(
                costService.summaryNecessaryCost(projectCost)
        );
        // 多余花费
        log.info("----计算机种[" + projectCost.getProjectName() + "]的多余花费");
        projectCost.setUnnecessaryCost(
                costService.summaryUnnecessaryCost(projectCost)
        );
    }
}
