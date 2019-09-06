package com.ivo.modules.coq.cost.formula;

import com.ivo.common.utils.SpringContextUtil;
import com.ivo.modules.coq.domain.ProjectCost;
import com.ivo.modules.coq.domain.ProjectStageCost;
import com.ivo.modules.coq.enums.StageTypeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 机种成本计算service
 * @Author: wj
 * @Date: 2019-07-28 15:10
 * @Version 1.0
 */
@Service
public class CostServiceImpl implements CostService {

    /**
     * 阶段计算公式bean名称
     */
    private static Map<String, String> map;

    @Autowired
    @Qualifier(value = "projectCostFormula")
    private ProjectCostFormula projectCostFormula;

    static {
        map = new HashMap<>();
        map.put(StageTypeEnum.NPRB.getStage(), "nprbStageCostFormula");
        map.put(StageTypeEnum.DESIGN.getStage(), "designStageCostFormula");
        map.put(StageTypeEnum.EVT.getStage(), "evtStageCostFormula");
        map.put(StageTypeEnum.DVT.getStage(), "dvtStageCostFormula");
        map.put(StageTypeEnum.PVT.getStage(), "pvtStageCostFormula");
        map.put("Default", "defaultStageCostFormula");
    }

    @Override
    public StageCostFormula getStageCostFormula(String stage) {
        // 阶段截取掉"-"及后内容
        stage = StringUtils.substringBefore(stage, "-");
        String beanName =  map.get(stage) == null ? map.get("Default") : map.get(stage);
        return (StageCostFormula) SpringContextUtil.getBean(beanName);
    }

    @Override
    public ProjectCostFormula getProjectCostFormula() {
        return projectCostFormula;
    }

    @Override
    public Double getDirectMaterialCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        return getStageCostFormula(stage).getDirectMaterialCost(projectName, stage, projectStageCost);
    }

    @Override
    public Double getToolCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        return getStageCostFormula(stage).getToolCost(projectName, stage, projectStageCost);
    }

    @Override
    public Double getValidationCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        return getStageCostFormula(stage).getValidationCost(projectName, stage, projectStageCost);
    }

    @Override
    public Double getProductionCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        return getStageCostFormula(stage).getProductionCost(projectName, stage, projectStageCost);
    }

    @Override
    public Double getReworkAndScrapCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        return getStageCostFormula(stage).getReworkAndScrapCost(projectName, stage, projectStageCost);
    }

    @Override
    public Double getSalaryCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        return getStageCostFormula(stage).getSalaryCost(projectName, stage, projectStageCost);
    }

    @Override
    public Double getRmaCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        return getStageCostFormula(stage).getRmaCost(projectName, stage, projectStageCost);
    }

    @Override
    public Double getObaCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        return getStageCostFormula(stage).getObaCost(projectName, stage, projectStageCost);
    }

    @Override
    public Double getTravelCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        return getStageCostFormula(stage).getTravelCost(projectName, stage, projectStageCost);
    }

    @Override
    public Double computePrecautionCost(ProjectStageCost projectStageCost) {
        return getStageCostFormula(projectStageCost.getStage()).computePrecautionCost(projectStageCost);
    }

    @Override
    public Double computeIdentifyCost(ProjectStageCost projectStageCost) {
        return getStageCostFormula(projectStageCost.getStage()).computeIdentifyCost(projectStageCost);
    }

    @Override
    public Double computeInLossCost(ProjectStageCost projectStageCost) {
        return getStageCostFormula(projectStageCost.getStage()).computeInLossCost(projectStageCost);
    }

    @Override
    public Double computeOutLossCost(ProjectStageCost projectStageCost) {
        return getStageCostFormula(projectStageCost.getStage()).computeOutLossCost(projectStageCost);
    }

    @Override
    public Double summaryNecessaryCost(ProjectCost projectCost) {
        return getProjectCostFormula().summaryNecessaryCost(projectCost);
    }

    @Override
    public Double summaryUnnecessaryCost(ProjectCost projectCost) {
        return getProjectCostFormula().summaryUnnecessaryCost(projectCost);
    }

    @Override
    public Double summaryPrecautionCost(ProjectCost projectCost) {
        return getProjectCostFormula().summaryPrecautionCost(projectCost);
    }

    @Override
    public Double summaryIdentifyCost(ProjectCost projectCost) {
        return getProjectCostFormula().summaryIdentifyCost(projectCost);
    }

    @Override
    public Double summaryInLossCost(ProjectCost projectCost) {
        return getProjectCostFormula().summaryInLossCost(projectCost);
    }

    @Override
    public Double summaryOutLossCost(ProjectCost projectCost) {
        return getProjectCostFormula().summaryOutLossCost(projectCost);
    }
}
