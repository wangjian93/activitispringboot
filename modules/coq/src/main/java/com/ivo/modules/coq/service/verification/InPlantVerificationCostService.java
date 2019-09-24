package com.ivo.modules.coq.service.verification;

import com.ivo.modules.coq.domain.verification.InPlantVerificationCost;

import java.util.List;

/**
 * 厂内验证费用service
 * @Author: wj
 * @Date: 2019-09-19 14:42
 * @Version 1.0
 */
public interface InPlantVerificationCostService {

    /**
     * 获取机种阶段的厂内的验证费用
     * @param project
     * @param stage
     * @return
     */
    Double computeInPlantVerificationCost(String project, String stage);

    /**
     * 获取机种的厂内验证费用详细
     * @param project
     * @return
     */
    List<InPlantVerificationCost> getInPlantVerificationCost(String project);

    /**
     * 同步厂内的机种验证费用
     * @param project
     * @return
     */
    List<InPlantVerificationCost> syncInPlantVerificationCost(String project);
}
