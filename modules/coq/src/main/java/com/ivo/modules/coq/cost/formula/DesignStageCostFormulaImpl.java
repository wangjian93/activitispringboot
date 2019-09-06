package com.ivo.modules.coq.cost.formula;

import com.ivo.modules.coq.cost.DoubleUtil;
import com.ivo.modules.coq.domain.ProjectStageCost;
import com.ivo.modules.coq.rest.RestService;
import com.ivo.modules.coq.service.MaterialPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 获取Design阶段的成本数据
 * @Author: wj
 * @Date: 2019-06-24 14:42
 * @Version 1.0
 */
@Service(value = "designStageCostFormula")
public class DesignStageCostFormulaImpl extends AbstractStageCostFormula {

    @Autowired
    private MaterialPriceService materialPriceService;

    @Autowired
    private RestService restService;

    /**
     * 本薪
     */
    private Double baseSalary = 6000D;

    /**
     * Design阶段的预防成本
     * 研发人员薪资 + 治工具
     * @param projectStageCost
     * @return
     */
    @Override
    public Double computePrecautionCost(ProjectStageCost projectStageCost) {
        return DoubleUtil.sum(projectStageCost.getSalaryCost(), projectStageCost.getToolCost());
    }

    /**
     * Design阶段的内损成本
     * 研人员薪资
     * @param projectStageCost
     * @return
     */
    @Override
    public Double computeInLossCost(ProjectStageCost projectStageCost) {
        List<Map<String, String>> memberList = restService.getMembers();


        return projectStageCost.getSalaryCost();
    }

    @Override
    public Double getDirectMaterialCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        return null;
    }

    @Override
    public Double getToolCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        return null;
    }

    @Override
    public Double getValidationCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        return null;
    }

    @Override
    public Double getProductionCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        return null;
    }

    @Override
    public Double getReworkAndScrapCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        return null;
    }

    /**
     * 薪资
     * @param projectName
     * @param stage
     * @param projectStageCost
     * @return
     */
    @Override
    public Double getSalaryCost(String projectName, String stage, ProjectStageCost projectStageCost) {

        return null;
    }

    @Override
    public Double getRmaCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        return null;
    }

    @Override
    public Double getObaCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        return null;
    }

    /**
     * 差旅费
     * @param projectName 机种
     * @param stage 阶段
     * @param projectStageCost
     * @return
     */
    @Override
    public Double getTravelCost(String projectName, String stage, ProjectStageCost projectStageCost) {
        return super.getTravelCost(projectName, stage, projectStageCost);
    }
}
