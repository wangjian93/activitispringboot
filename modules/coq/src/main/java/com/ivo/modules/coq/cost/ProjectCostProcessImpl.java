//package com.ivo.modules.coq.cost;
//
//import com.ivo.modules.coq.domain.PhaseCost;
//import com.ivo.modules.coq.domain.ProjectCost3;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//
///**
// *
// * @Author: wj
// * @Date: 2019-06-26 13:42
// * @Version 1.0
// */
//@Slf4j
//@Service
//public class ProjectCostProcessImpl extends PhaseCostProcessImpl implements ProjectCostProcess {
//
//
//    /**
//     * 计算机种的成本（预防成本，鉴定成本，内损成本，外损成本，必要花费，多余花费）
//     * @param projectCost3
//     */
//    @Override
//    public void computeProjectCost(ProjectCost3 projectCost3) {
//        List<PhaseCost> phaseCostList = projectCost3.getPhaseCosts();
//
//        BigDecimal precautionCost = new BigDecimal(0);
//        BigDecimal identifyCost = new BigDecimal(0);
//        BigDecimal inLossCost = new BigDecimal(0);
//        BigDecimal outLossCost = new BigDecimal(0);
//
//        // 统计预防成本，鉴定成本，内损成本，外损成本
//        for(PhaseCost phaseCost : phaseCostList) {
//            Double precautionCost_Detail = phaseCost.getPrecautionCost();
//            if(precautionCost_Detail != null) {
//                precautionCost = precautionCost.add(new BigDecimal(precautionCost_Detail));
//            }
//
//            Double identifyCost_Detail = phaseCost.getIdentifyCost();
//            if(identifyCost_Detail != null) {
//                identifyCost = identifyCost.add(new BigDecimal(identifyCost_Detail));
//            }
//
//            Double inLossCost_Detail = phaseCost.getInLossCost();
//            if(inLossCost_Detail != null) {
//                inLossCost = inLossCost.add(new BigDecimal(inLossCost_Detail));
//            }
//
//            Double outLossCost_Detail = phaseCost.getOutLossCost();
//            if(outLossCost_Detail != null) {
//                outLossCost = outLossCost.add(new BigDecimal(outLossCost_Detail));
//            }
//        }
//
//        // 统计必要的花费
//        BigDecimal necessaryCost = precautionCost.add(identifyCost);
//        // 统计多余的花费
//        BigDecimal unnecessaryCost = inLossCost.add(outLossCost);
//
//        projectCost3.setPrecautionCost(precautionCost.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
//        projectCost3.setIdentifyCost(identifyCost.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
//        projectCost3.setInLossCost(inLossCost.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
//        projectCost3.setOutLossCost(outLossCost.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
//        projectCost3.setNecessaryCost(necessaryCost.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
//        projectCost3.setUnnecessaryCost(unnecessaryCost.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
//
//        log.info("完成计算机种的成本（预防成本，鉴定成本，内损成本，外损成本，必要花费，多余花费）");
//    }
//}
