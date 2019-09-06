package com.ivo.modules.coq.cost2.impl;

import com.ivo.modules.coq.cost.DoubleUtil;
import com.ivo.modules.coq.cost2.SecondarySubjectMethod;
import com.ivo.modules.coq.domain.ProjectSecondarySubjectCost2;
import com.ivo.modules.coq.domain.ProjectStageCost2;
import com.ivo.modules.coq.enums.StageTypeEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @Author: wj
 * @Date: 2019-09-01 22:17
 * @Version 1.0
 */
public abstract class AbstractSecondarySubjectMethod implements SecondarySubjectMethod {
    @Override
    public void setPrecautionCost(ProjectSecondarySubjectCost2 projectSecondarySubjectCost, List<ProjectStageCost2> list) {
        Double precautionCost = null;
        for(ProjectStageCost2 projectStageCost : list) {
            if(StringUtils.containsIgnoreCase(projectStageCost.getStage(), projectSecondarySubjectCost.getStage())) {
                if(StringUtils.containsIgnoreCase(projectStageCost.getStage(), StageTypeEnum.NPRB.getStage())) {
                    // 研发人员薪资 + 差旅费
                    precautionCost = DoubleUtil.sum(precautionCost,
                            projectStageCost.getSalaryCost(),
                            projectStageCost.getTravelCost());
                }
                if(StringUtils.containsIgnoreCase(projectStageCost.getStage(), StageTypeEnum.DESIGN.getStage())) {
                    // 研发人员薪资 + 治工具
                    precautionCost = DoubleUtil.sum(precautionCost,
                            projectStageCost.getSalaryCost(),
                            projectStageCost.getToolCost());
                }
                if(StringUtils.containsIgnoreCase(projectStageCost.getStage(), StageTypeEnum.EVT.getStage())) {
                    // EVT1直接材料 + EVT1治工具 + EVT1生产费用
                    if(!StringUtils.equalsIgnoreCase(projectStageCost.getStage(), "EVT1")) {
                        continue;
                    }
                    precautionCost = DoubleUtil.sum(precautionCost,
                            projectStageCost.getDirectMaterialCost(),
                            projectStageCost.getToolCost(),
                            projectStageCost.getProductionCost());
                }
                if(StringUtils.containsIgnoreCase(projectStageCost.getStage(), StageTypeEnum.DVT.getStage())) {
                    // DVT1直接材料 + DVT1治工具 + DVT1生产费用
                    if(!StringUtils.equalsIgnoreCase(projectStageCost.getStage(), "DVT1")) {
                        continue;
                    }
                    precautionCost = DoubleUtil.sum(precautionCost,
                            projectStageCost.getDirectMaterialCost(),
                            projectStageCost.getToolCost(),
                            projectStageCost.getProductionCost());
                }
                if(StringUtils.containsIgnoreCase(projectStageCost.getStage(), StageTypeEnum.PVT.getStage())) {
                    // 直接材料 + 治工具 + 生产费用
                    precautionCost = DoubleUtil.sum(precautionCost,
                            projectStageCost.getToolCost(),
                            projectStageCost.getProductionCost());
                }
            }
        }
        projectSecondarySubjectCost.setPrecautionCost(precautionCost);
    }

    @Override
    public void setIdentifyCost(ProjectSecondarySubjectCost2 projectSecondarySubjectCost, List<ProjectStageCost2> list) {
        Double identifyCost = null;
        for(ProjectStageCost2 projectStageCost : list) {
            if(StringUtils.containsIgnoreCase(projectStageCost.getStage(), projectSecondarySubjectCost.getStage())) {
                if(StringUtils.containsIgnoreCase(projectStageCost.getStage(), StageTypeEnum.NPRB.getStage())) {

                }
                if(StringUtils.containsIgnoreCase(projectStageCost.getStage(), StageTypeEnum.DESIGN.getStage())) {
                    // 验证费用
                    identifyCost = DoubleUtil.sum(identifyCost,
                            projectStageCost.getValidationCost());
                }
                if(StringUtils.containsIgnoreCase(projectStageCost.getStage(), StageTypeEnum.EVT.getStage())) {
                    // 验证费用
                    identifyCost = DoubleUtil.sum(identifyCost,
                            projectStageCost.getValidationCost());
                }
                if(StringUtils.containsIgnoreCase(projectStageCost.getStage(), StageTypeEnum.DVT.getStage())) {

                }
                if(StringUtils.containsIgnoreCase(projectStageCost.getStage(), StageTypeEnum.PVT.getStage())) {

                }
            }
        }
        projectSecondarySubjectCost.setIdentifyCost(identifyCost);
    }

    @Override
    public void setInLossCost(ProjectSecondarySubjectCost2 projectSecondarySubjectCost, List<ProjectStageCost2> list) {
        Double inLossCost = null;
        for(ProjectStageCost2 projectStageCost : list) {
            if(StringUtils.containsIgnoreCase(projectStageCost.getStage(), projectSecondarySubjectCost.getStage())) {
                if(StringUtils.containsIgnoreCase(projectStageCost.getStage(), StageTypeEnum.NPRB.getStage())) {
                    // 差旅费
                    inLossCost = DoubleUtil.sum(inLossCost,
                            projectStageCost.getTravelCost());
                }
                if(StringUtils.containsIgnoreCase(projectStageCost.getStage(), StageTypeEnum.DESIGN.getStage())) {
                    // 研人员薪资
                    inLossCost = DoubleUtil.sum(inLossCost,
                            projectStageCost.getSalaryCost());
                }
                if(StringUtils.containsIgnoreCase(projectStageCost.getStage(), StageTypeEnum.EVT.getStage())) {
                    // 直接材料成本 + 治工具费用 + 生产费用 + 重工报废费用 + 验证费用
                    inLossCost = DoubleUtil.sum(inLossCost,
                            projectStageCost.getDirectMaterialCost(),
                            projectStageCost.getToolCost(),
                            projectStageCost.getProductionCost(),
                            projectStageCost.getReworkAndScrapCost(),
                            projectStageCost.getValidationCost());
                }
                if(StringUtils.containsIgnoreCase(projectStageCost.getStage(), StageTypeEnum.DVT.getStage())) {
                    // 直接材料成本 + 治工具费用 + 生产费用 + 重工报废费用 + 验证费用
                    inLossCost = DoubleUtil.sum(inLossCost,
                            projectStageCost.getDirectMaterialCost(),
                            projectStageCost.getToolCost(),
                            projectStageCost.getProductionCost(),
                            projectStageCost.getReworkAndScrapCost(),
                            projectStageCost.getValidationCost());
                }
                if(StringUtils.containsIgnoreCase(projectStageCost.getStage(), StageTypeEnum.PVT.getStage())) {
                    // 直接材料成本 + 治工具费用 + 生产费用 + 重工报废费用 + 验证费用
                    inLossCost = DoubleUtil.sum(inLossCost,
                            projectStageCost.getDirectMaterialCost(),
                            projectStageCost.getToolCost(),
                            projectStageCost.getProductionCost(),
                            projectStageCost.getReworkAndScrapCost(),
                            projectStageCost.getValidationCost());
                }
            }
        }
        projectSecondarySubjectCost.setInLossCost(inLossCost);
    }

    @Override
    public void setOutLossCost(ProjectSecondarySubjectCost2 projectSecondarySubjectCost, List<ProjectStageCost2> list) {
        Double outLossCost = null;
        for(ProjectStageCost2 projectStageCost : list) {
            if(StringUtils.containsIgnoreCase(projectStageCost.getStage(), projectSecondarySubjectCost.getStage())) {
                if(StringUtils.containsIgnoreCase(projectStageCost.getStage(), StageTypeEnum.NPRB.getStage())) {

                }
                if(StringUtils.containsIgnoreCase(projectStageCost.getStage(), StageTypeEnum.DESIGN.getStage())) {

                }
                if(StringUtils.containsIgnoreCase(projectStageCost.getStage(), StageTypeEnum.EVT.getStage())) {

                }
                if(StringUtils.containsIgnoreCase(projectStageCost.getStage(), StageTypeEnum.DVT.getStage())) {

                }
                if(StringUtils.containsIgnoreCase(projectStageCost.getStage(), StageTypeEnum.PVT.getStage())) {
                    // OBA费用 + RMA费用
                    outLossCost = DoubleUtil.sum(outLossCost,
                            projectStageCost.getObaCost(),
                            projectStageCost.getRmaCost());
                }
            }
        }
        projectSecondarySubjectCost.setOutLossCost(outLossCost);
    }
}
