package com.ivo.modules.coq.cost.formula;

import com.ivo.modules.coq.cost.DoubleUtil;
import com.ivo.modules.coq.domain.PhaseCostDetail;

/**
 * 获取Dvt阶段的成本数据
 * @Author: wj
 * @Date: 2019-06-24 14:45
 * @Version 1.0
 */
public class DvtCost implements Cost {

    /**
     * DVT阶段直接材料成本接口
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    @Override
    public Double getDirectMaterialCost(String projectName, String phase) {
        //Todo... DVT阶段直接材料成本接口
        Double directMaterialCost;
        switch (phase) {
            case "DVT-1" : directMaterialCost = 383675.62; break;
            case "DVT-2" : directMaterialCost = 567360.92; break;
            case "DVT-3" : directMaterialCost = 613066.54; break;
            case "DVT-4" : directMaterialCost = 764376.48; break;
            case "DVT-5" : directMaterialCost = 483211.54; break;
            case "DVT-6" : directMaterialCost = 531533.38; break;
            default: directMaterialCost = null;
        }
        return directMaterialCost;
    }

    /**
     * DVT阶段治工具成本接口
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    @Override
    public Double getToolCost(String projectName, String phase) {
        //Todo... DVT阶段治工具成本接口
        Double toolCost;
        switch (phase) {
            case "DVT-1" : toolCost = 171453.34; break;
            case "DVT-2" : case "DVT-3" : case "DVT-4" : case "DVT-5" : case "DVT-6" : toolCost = 0D; break;
            default: toolCost = null;
        }
        return toolCost;
    }

    /**
     * DVT阶段验证费用成本接口
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    @Override
    public Double getValidationCost(String projectName, String phase) {
        //Todo... DVT阶段验证费用成本接口
        Double validationCost;
        switch (phase) {
            case "DVT-1" : validationCost = 13621.61; break;
            case "DVT-2" : validationCost = 2756.35; break;
            case "DVT-3" : validationCost = 16338.91; break;
            case "DVT-4" : validationCost = 15329.38; break;
            case "DVT-5" : validationCost = 0D; break;
            case "DVT-6" : validationCost = 12428.50; break;
            default: validationCost = null;
        }
        return validationCost;
    }

    /**
     * DVT阶段生产费用成本接口
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    @Override
    public Double getProductionCost(String projectName, String phase) {
        //Todo... DVT阶段生产费用成本接口
        Double productionCost;
        switch (phase) {
            case "DVT-1" : productionCost = 20379.44; break;
            case "DVT-2" : productionCost = 32688.04; break;
            case "DVT-3" : productionCost = 32250.14; break;
            case "DVT-4" : productionCost = 37948.83; break;
            case "DVT-5" : productionCost = 27490D; break;
            case "DVT-6" : productionCost = 30239D; break;
            default: productionCost = null;
        }
        return productionCost;
    }

    /**
     * DVT阶段重工报废费用成本接口
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    @Override
    public Double getReworkAndScrapCost(String projectName, String phase) {
        //TODO... DVT阶段重工报废费用成本接口
        Double reworkAndScrapCos;
        switch (phase) {
            case "DVT-1" : reworkAndScrapCos = 1652.92; break;
            case "DVT-2" : reworkAndScrapCos = 51390.78; break;
            case "DVT-3" : reworkAndScrapCos = 317.96; break;
            case "DVT-4" : reworkAndScrapCos = 540.62; break;
            case "DVT-5" : reworkAndScrapCos = 1234.52; break;
            case "DVT-6" : reworkAndScrapCos = 570.02; break;
            default: reworkAndScrapCos = null;
        }
        return reworkAndScrapCos;
    }

    /**
     * DVT阶段研发人员薪资成本接口
     * @param projectName
     * @param phase
     * @return
     */
    @Override
    public Double getSalaryCost(String projectName, String phase) {
        //TODO... DVT阶段研发人员薪资成本接口
        return null;
    }

    /**
     * DVT阶段RMA成本接口
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    @Override
    public Double getRmaCost(String projectName, String phase) {
        //TODO... DVT阶段RMA成本接口
        return null;
    }

    /**
     * DVT阶段OBA成本接口
     * @param projectName
     * @param phase
     * @return
     */
    @Override
    public Double getObaCost(String projectName, String phase) {
        //TODO... DVT阶段OBA成本接口
        return null;
    }

    /**
     * DVT阶段差旅费成本接口
     * @param projectName 机种
     * @param phase 阶段
     * @return
     */
    @Override
    public Double getTravelCost(String projectName, String phase) {
        //TODO... DVT阶段差旅费成本接口
        return 0D;
    }

    /**
     * DVT阶段预防成本计算
     * 预防成本 （DVT-1 : 直接材料成本 + 治工具成本 + 生产费用成本）
     * @param phaseCostDetail 机种成本明细
     * @param phase 阶段
     * @return
     */
    @Override
    public Double computePrecautionCost(PhaseCostDetail phaseCostDetail,  String phase) {
        //TODO... DVT阶段预防成本计算
        // 预防成本 （DVT-1: 直接材料成本 + 治工具成本 + 生产费用成本)
        Double precautionCost;
        switch (phase) {
            case "DVT-1" :
                Double directMaterialCost = phaseCostDetail.getDirectMaterialCost();
                Double toolCost = phaseCostDetail.getToolCost();
                Double productionCost = phaseCostDetail.getProductionCost();
                precautionCost = DoubleUtil.sum(directMaterialCost, toolCost, productionCost);
                break;
            default: precautionCost = null;
        }

        return precautionCost;
    }

    /**
     * DVT阶段鉴定成本计算
     * 鉴定成本 （DVT-1: 验证费用）
     * @param phaseCostDetail 机种成本明细
     * @param phase 阶段
     * @return
     */
    @Override
    public Double computeIdentifyCost(PhaseCostDetail phaseCostDetail, String phase) {
        //TODO... DVT阶段鉴定成本计算
        // 鉴定成本 （DVT-1: 验证费用）
        Double identifyCost;
        switch (phase) {
            case "DVT-1" :
                Double validationCost = phaseCostDetail.getValidationCost();
                identifyCost = validationCost;
                break;
            default: identifyCost = null;
        }

        return identifyCost;
    }

    /**
     * DVT阶段内损成本计算
     * 内损成本 （DVT-1: 重工报废费用 + 差旅费;
     * DVT-2,DVT-3,DVT-4,DVT-5,DVT-6: 直接材料成本 + 治工具成本 + 验证费用 + 生产费用 + 重工报废费用 + 差旅费）
     * @param phaseCostDetail 机种成本明细
     * @param phase 阶段
     * @return
     */
    @Override
    public Double computeInLossCost(PhaseCostDetail phaseCostDetail, String phase) {
        //TODO... DVT阶段内损成本计算
        // 内损成本 （DVT-1: 重工报废费用 + 差旅费;
        // DVT-2,DVT-3,DVT-4,DVT-5,DVT-6: 直接材料成本 + 治工具成本 + 验证费用 + 生产费用 + 重工报废费用 + 差旅费）
        Double inLossCost;
        switch(phase) {
            case "DVT-1" :
                Double reworkAndScrapCost = phaseCostDetail.getReworkAndScrapCost();
                Double travelCost = phaseCostDetail.getTravelCost();
                inLossCost = DoubleUtil.sum(reworkAndScrapCost, travelCost);
                break;
            case "DVT-2" : case "DVT-3" : case "DVT-4" : case "DVT-5" : case "DVT-6" :
                Double directMaterialCost = phaseCostDetail.getDirectMaterialCost();
                Double toolCost = phaseCostDetail.getToolCost();
                Double validationCost = phaseCostDetail.getValidationCost();
                Double productionCost = phaseCostDetail.getProductionCost();
                Double reworkAndScrapCost1 = phaseCostDetail.getReworkAndScrapCost();
                Double travelCost1 = phaseCostDetail.getTravelCost();
                inLossCost = DoubleUtil.sum(directMaterialCost, toolCost, validationCost, productionCost,
                        reworkAndScrapCost1, travelCost1);
                break;
            default: inLossCost = null;
        }
        return inLossCost;
    }

    /**
     * DVT阶段外损成本计算
     * @param phaseCostDetail 机种成本明细
     * @param phase 阶段
     * @return
     */
    @Override
    public Double computeOutLossCost(PhaseCostDetail phaseCostDetail, String phase) {
        //TODO... DVT阶段外损成本计算
        return null;
    }
}
