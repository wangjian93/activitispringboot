package com.ivo.modules.coq.service.impl.verification;

import com.ivo.modules.coq.cost.DoubleUtil;
import com.ivo.modules.coq.domain.BmProjectCost;
import com.ivo.modules.coq.domain.verification.InPlantVerificationCost;
import com.ivo.modules.coq.domain.verification.VerificationBasicDate;
import com.ivo.modules.coq.domain.verification.VerificationSubject;
import com.ivo.modules.coq.repository.verification.InPlantVerificationCostRepository;
import com.ivo.modules.coq.rest.RestService;
import com.ivo.modules.coq.service.verification.InPlantVerificationCostService;
import com.ivo.modules.coq.service.verification.VerificationSubjectAndMachineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: wj
 * @Date: 2019-09-19 14:51
 * @Version 1.0
 */
@Service
@Slf4j
public class InPlantVerificationCostServiceImpl implements InPlantVerificationCostService {

    @Autowired
    private InPlantVerificationCostRepository repository;

    @Autowired
    private RestService restService;

    @Autowired
    private VerificationSubjectAndMachineService subjectAndMachineService;

    @Override
    public Double computeInPlantVerificationCost(String project, String stage) {
        log.info("计算机种" + project + "阶段" + stage + "的厂内验证成本");
        Double cost = null;
        List<InPlantVerificationCost> list = getInPlantVerificationCost(project, stage);
        if(list == null && list.size()>0) {
            return null;
        }
        for(InPlantVerificationCost verificationCost : list) {
            cost = DoubleUtil.sum(cost, verificationCost.getManPowerCost(), verificationCost.getMaintainCost(),
                    verificationCost.getElectricityBill());
        }
        return cost;
    }

    @Override
    public List<InPlantVerificationCost> getInPlantVerificationCost(String project) {
        return repository.findByProject(project);
    }

    private List<InPlantVerificationCost> getInPlantVerificationCost(String project, String stage) {
        return repository.findByProjectAndStage(project, stage);
    }

    @Override
    public List<InPlantVerificationCost> syncInPlantVerificationCost(String project) {
        List<InPlantVerificationCost> oldList = getInPlantVerificationCost(project);
        repository.deleteAll(oldList);

        List<Map<String, Object>> mapList = restService.getQmsVerefication(project);

        List<InPlantVerificationCost> list = new ArrayList<>();
        mapList.forEach(map -> {
            InPlantVerificationCost inPlantVerificationCost = new InPlantVerificationCost(project);
            analyseInPlantVerificationCost(inPlantVerificationCost, map);
            computeVerification(inPlantVerificationCost);
            list.add(inPlantVerificationCost);
        });
        repository.saveAll(list);
        return list;
    }

    private void analyseInPlantVerificationCost(InPlantVerificationCost inPlantVerificationCost, Map<String, Object> map) {
        inPlantVerificationCost.setStage((String)map.get("stage"));
        inPlantVerificationCost.setVerificationSubject(((String) map.get("verificationSubject")));
        inPlantVerificationCost.setVerificationType(((String) map.get("verificationType")));
        inPlantVerificationCost.setVerificationCondition(((String) map.get("verificationCondition")));
        inPlantVerificationCost.setQuantity((Double.valueOf((String)map.get("quantity"))));
    }

    public void computeVerification(InPlantVerificationCost inPlantVerificationCost) {
        VerificationSubject subject = subjectAndMachineService.getVerificationSubject(inPlantVerificationCost.getVerificationSubject());
        if(subject == null) {
            return;
        }
        inPlantVerificationCost.setMachineName(subject.getMachineName());
        inPlantVerificationCost.setVerificationTime(subject.getVerificationTime());
        inPlantVerificationCost.setUnit(subject.getUnit());
        inPlantVerificationCost.setType(subject.getType());

        VerificationBasicDate basicDate = subjectAndMachineService.getVerificationBasicDate(2017);
        inPlantVerificationCost.setManPowerCostPer(basicDate.getManPowerCostPer());
        inPlantVerificationCost.setMaintainCostPer(basicDate.getMaintainCostPer());
        inPlantVerificationCost.setElectricityBillPer(subject.getElectricityBillPer());

        inPlantVerificationCost.setManPowerCost(
                DoubleUtil.multiply(inPlantVerificationCost.getQuantity(), inPlantVerificationCost.getManPowerCostPer())
        );
        inPlantVerificationCost.setMaintainCost(
                DoubleUtil.multiply(inPlantVerificationCost.getQuantity(), inPlantVerificationCost.getMaintainCostPer())
        );
        inPlantVerificationCost.setElectricityBill(
                DoubleUtil.multiply(inPlantVerificationCost.getQuantity(), inPlantVerificationCost.getElectricityBillPer())
        );
    }
}
