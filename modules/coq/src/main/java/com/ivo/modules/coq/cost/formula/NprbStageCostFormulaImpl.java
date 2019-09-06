package com.ivo.modules.coq.cost.formula;

import com.ivo.modules.coq.cost.DoubleUtil;
import com.ivo.modules.coq.domain.PlmProjectMember;
import com.ivo.modules.coq.domain.ProjectStageCost;
import com.ivo.modules.coq.rest.RestService;
import com.ivo.modules.coq.service.PlmProjectMemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 获取NPRB阶段的成本数据
 * @Author: wj
 * @Date: 2019-06-24 14:19
 * @Version 1.0
 */
@Service(value = "nprbStageCostFormula")
public class NprbStageCostFormulaImpl extends AbstractStageCostFormula {

    @Autowired
    private RestService restService;

    @Autowired
    private PlmProjectMemberService memberService;

    @Override
    public Double getSalaryCost(String projectName, String stage, ProjectStageCost projectStageCost) {

//        List<Map<String, String>> memberList = restService.getMembers();




        List<PlmProjectMember> memberList = memberService.getPlmMemberList(projectName);

        Double sum = null;

        for(PlmProjectMember member : memberList) {
            int tianshu = 0;
            if(StringUtils.equalsAnyIgnoreCase(member.getRole(), "PM", "PJM", "LCD RD")) {
                tianshu = 6;
            } else if(StringUtils.equalsAnyIgnoreCase(member.getRole(), "EE RD", "ME RD", "RD-Packing",
                    "NPE-Array", "NPE-Cell", "NPE-Lcm")) {
                tianshu = 3;
            } else if(StringUtils.equalsAnyIgnoreCase(member.getRole(), "LCM TEC")) {
                tianshu = 5;
            }
            sum = DoubleUtil.sum(sum, 6000D/21.5*tianshu);
        }

        //PM
        // PJM
        //LCD RD

        //EE RD
        //ME RD
        //RD-Packing
        //NPE-Cell
        //NPE-Array
        //NPE-Lcm


        //LCM TEC

        return sum;
    }

    /**
     * NPRB阶段的预防成本
     * 研发人员薪资 + 差旅费
     * @param projectStageCost
     * @return
     */
    @Override
    public Double computePrecautionCost(ProjectStageCost projectStageCost) {
        return DoubleUtil.sum(projectStageCost.getSalaryCost() , projectStageCost.getTravelCost());
    }

    /**
     * NPRB阶段的内损成本
     * 差旅费
     * @param projectStageCost
     * @return
     */
    @Override
    public Double computeInLossCost(ProjectStageCost projectStageCost) {
        return projectStageCost.getTravelCost();
    }
}
