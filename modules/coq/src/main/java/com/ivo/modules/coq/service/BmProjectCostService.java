package com.ivo.modules.coq.service;

import com.ivo.modules.coq.domain.BmProjectCost;

import java.util.List;

/**
 * BM机种成本数据service
 * @Author: wj
 * @Date: 2019-08-22 10:27
 * @Version 1.0
 */
public interface BmProjectCostService {


    /**
     * 从BM获取机种阶段的治工具费用
     * @param project
     * @param stage
     * @return
     */
    Double getToolCostFromBm(String project, String stage);


    /**
     * 从BM获取机种阶段的直接材料（外包薄化）
     * @param project
     * @param stage
     * @return
     */
    Double getDirectMaterialCost_thin(String project, String stage);

    /**
     * 从BM获取机种阶段的验证费用
     * @param project
     * @param stage
     * @return
     */
    Double getValidationCost(String project, String stage);


    /**
     * 获取治工具费用部分的明细
     * @param project
     * @return
     */
    List<BmProjectCost> getDetailForToolCost(String project);

    /**
     * 获取直接材料（薄化）部分的明细
     * @param project
     * @return
     */
    List<BmProjectCost> getDetailForDirectMaterialCost_thin(String project);
    /**
     * 获取验证费用部分的明细
     * @param project
     * @return
     */
    List<BmProjectCost> getDetailForValidationCost(String project);

    /**
     * 从BM同步费用数据
     * @param project
     * @return
     */
    List<BmProjectCost> syncBmProjectCost(String project);


    List<BmProjectCost> getBmProjectCost(String project);
}
