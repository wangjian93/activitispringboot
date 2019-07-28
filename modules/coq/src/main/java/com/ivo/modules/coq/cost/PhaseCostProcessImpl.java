//package com.ivo.modules.coq.cost;
//
//import com.ivo.modules.coq.cost.formula.*;
//import com.ivo.modules.coq.domain.PhaseCost;
//import com.ivo.modules.coq.domain.PhaseCostDetail;
//import org.apache.commons.lang3.StringUtils;
//
//import java.math.BigDecimal;
//import java.util.*;
//
///**
// * @Author: wj
// * @Date: 2019-06-26 14:47
// * @Version 1.0
// */
//public class PhaseCostProcessImpl extends PhaseCostDetailProcessImpl implements PhaseCostProcess {
//
//    @Override
//    public List<PhaseCost> computePhaseCost(List<PhaseCostDetail> list) {
//
//        List<PhaseCost> phaseCosts = new ArrayList<>();
//
//        for(PhaseCostDetail phaseCostDetail : list) {
//            String phase = phaseCostDetail.getPhase();
//
//            StageCostFormula cost = CostAdaptive.getCost(phase);
//
//            PhaseCost phaseCost = new PhaseCost(phase);
//            phaseCost.setPrecautionCost(cost.computePrecautionCost(phaseCostDetail, phase));
//            phaseCost.setIdentifyCost(cost.computeIdentifyCost(phaseCostDetail, phase));
//            phaseCost.setInLossCost(cost.computeInLossCost(phaseCostDetail, phase));
//            phaseCost.setOutLossCost(cost.computeOutLossCost(phaseCostDetail, phase));
//
//            phaseCosts.add(phaseCost);
//        }
//
//        return mergePhase(phaseCosts);
//    }
//
//    /**
//     * 阶段合并成本
//     * @return
//     */
//    private List<PhaseCost> mergePhase(List<PhaseCost> list) {
//
//        Map<String, PhaseCost> map = new LinkedHashMap<>();
//
//        for(PhaseCost phaseCost : list) {
//            String phase = phaseCost.getPhase();
//            // 阶段截取掉"-"及后内容
//            phase = StringUtils.substringBefore(phase, "-");
//
//            if(map.get(phase) == null) {
//                PhaseCost phaseCost1 = new PhaseCost(phase);
//                phaseCost1.setPrecautionCost(phaseCost.getPrecautionCost());
//                phaseCost1.setIdentifyCost(phaseCost.getIdentifyCost());
//                phaseCost1.setInLossCost(phaseCost.getInLossCost());
//                phaseCost1.setOutLossCost(phaseCost.getOutLossCost());
//                map.put(phase, phaseCost1);
//            } else {
//                PhaseCost phaseCost1 = map.get(phase);
//
//                if(phaseCost.getPrecautionCost() != null) {
//                    BigDecimal precautionCost;
//                    if(phaseCost1.getPrecautionCost() == null) {
//                        precautionCost = new BigDecimal(0);
//                    } else {
//                        precautionCost = new BigDecimal(phaseCost1.getPrecautionCost());
//                    }
//                    precautionCost = precautionCost.add(new BigDecimal(phaseCost.getPrecautionCost()));
//                    phaseCost1.setPrecautionCost(precautionCost.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
//                }
//
//                if(phaseCost.getIdentifyCost() != null) {
//                    BigDecimal identifyCost = null;
//                    if(phaseCost1.getIdentifyCost() == null) {
//                        identifyCost = new BigDecimal(0);
//                    } else {
//                        identifyCost = new BigDecimal(phaseCost1.getIdentifyCost());
//                    }
//                    identifyCost = identifyCost.add(new BigDecimal(phaseCost.getIdentifyCost()));
//                    phaseCost1.setIdentifyCost(identifyCost.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
//                }
//
//                if(phaseCost.getInLossCost() != null) {
//                    BigDecimal inLossCost;
//                    if(phaseCost1.getInLossCost() == null) {
//                        inLossCost = new BigDecimal(0);
//                    } else {
//                        inLossCost = new BigDecimal(phaseCost1.getInLossCost());
//                    }
//                    inLossCost = inLossCost.add(new BigDecimal(phaseCost.getInLossCost()));
//                    phaseCost1.setInLossCost(inLossCost.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
//                }
//
//                if(phaseCost.getOutLossCost() != null) {
//                    BigDecimal outLossCost;
//                    if(phaseCost1.getOutLossCost() == null) {
//                        outLossCost = new BigDecimal(0);
//                    } else {
//                        outLossCost = new BigDecimal(phaseCost1.getOutLossCost());
//                    }
//                    outLossCost = outLossCost.add(new BigDecimal(phaseCost.getOutLossCost()));
//                    phaseCost1.setOutLossCost(outLossCost.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
//                }
//
//            }
//        }
//
//        return new ArrayList<>(map.values());
//    }
//}
