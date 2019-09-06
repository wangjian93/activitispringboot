package com.ivo.modules.coq.cost2;

import com.ivo.modules.coq.domain.ProjectSecondarySubjectCost2;
import com.ivo.modules.coq.domain.ProjectStageCost2;

import java.util.List;

/**
 * 机种成本二级科目的计算方式
 * @Author: wj
 * @Date: 2019-09-01 22:12
 * @Version 1.0
 */
public interface SecondarySubjectMethod {

    /**
     * 预防成本
     */
    void setPrecautionCost(ProjectSecondarySubjectCost2 projectSecondarySubjectCost, List<ProjectStageCost2> list);

    /**
     * 鉴定成本
     */
    void setIdentifyCost(ProjectSecondarySubjectCost2 projectSecondarySubjectCost, List<ProjectStageCost2> list);

    /**
     * 内损成本
     */
    void setInLossCost(ProjectSecondarySubjectCost2 projectSecondarySubjectCost, List<ProjectStageCost2> list);

    /**
     * 外损成本
     */
    void setOutLossCost(ProjectSecondarySubjectCost2 projectSecondarySubjectCost, List<ProjectStageCost2> list);
}
