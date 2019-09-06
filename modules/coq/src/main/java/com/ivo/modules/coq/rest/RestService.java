package com.ivo.modules.coq.rest;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 访问外系统接口服务
 * @Author: wj
 * @Date: 2019-07-25 10:55
 * @Version 1.0
 */
public interface RestService {

    /**
     * PLM接口-1
     * 根据机种从PLM系统获取机种的阶段信息
     * @param project 机种
     */
    List<Map<String, String>> getProjectStage(String project);

    List<Map<String, String>> getMembers();


    /**
     * PLM接口
     * 从PLM系统中获取机种的阶段信息
     * @param project
     * @return
     */
    List<Map<String, String>> getStageFromPlm(String project);

    /**
     * PLM接口
     * 从PLM系统中获取机种的进度时间
     * @param project
     * @return
     */
    List<Map<String, String>> getScheduleFromPlm(String project);

    /**
     * PLM接口
     * 从PLM系统中获取机种的项目成员
     * @param project
     * @return
     */
    List<Map<String, String>> getMembersFromPlm(String project);

    /**
     * BM接口
     * 从BM系统获取治工具-资本支出类费用
     * @param project
     * @param fromDate
     * @param toDate
     * @return
     */
    List<Map<String, Object>> getToolCapitalFromBm(String project, Date fromDate, Date toDate);

    /**
     * BM接口
     * 从BM系统获取治工具-其他费用
     * @param project
     * @param fromDate
     * @param toDate
     * @return
     */
    List<Map<String, Object>> getToolOtherFromBm(String project, Date fromDate, Date toDate);

    /**
     * BM接口
     * 从BM系统获取直接材料成本（外包薄化）
     * @param project
     * @param fromDate
     * @param toDate
     * @return
     */
    List<Map<String, Object>> getDirectMaterialCost_thinFromBm(String project, Date fromDate, Date toDate);

    /**
     * BM接口
     * 从BM系统获取验证费用
     * @param project
     * @param fromDate
     * @param toDate
     * @return
     */
    List<Map<String, Object>> getValidationCostFromBm(String project, Date fromDate, Date toDate);

    /**
     * EE单接口
     * 从ED获取到EE单号
     */
    String getEeFromEd(String ed);

    /**
     * EE单接口
     * 从EE中获取ProdId
     * @param ee
     * @return
     */
    String getProdIdFromEE(String ee);

    /**
     * EE单接口
     * 从EE单中获取pfcd tft cf
     * @param ee
     * @return
     */
    Map<String, String> getPfcdFromEE(String ee);

    /**
     * EE单接口
     * 从EE中获取到工单
     * @param ee
     * @return
     */
    List<String> getWoFromEE(String ee);

    /**
     * BOM接口
     * 根据prodId获取料号和版本
     * @param prodId
     * @return
     */
    Map<String, String> getMaterialAndVersion(String prodId);

    /**
     * BOM接口
     * Array根据prodId获取料号价格
     * @param prodId
     * @return
     */
    Double getMaterialPriceForArrayFromBom(String prodId);

    /**
     * BOM接口
     * Cell根据prodId（PFCD）获取料号价格
     * @param prodId
     * @return
     */
    Double getMaterialPriceForCellFromBom(String prodId);

    Double getWoCostForModuleFromBom(String wo);

}
