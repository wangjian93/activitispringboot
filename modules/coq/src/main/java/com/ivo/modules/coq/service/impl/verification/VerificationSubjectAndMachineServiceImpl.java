package com.ivo.modules.coq.service.impl.verification;

import com.ivo.modules.coq.cost.DoubleUtil;
import com.ivo.modules.coq.domain.verification.HumitureMachine;
import com.ivo.modules.coq.domain.verification.OtherMachine;
import com.ivo.modules.coq.domain.verification.VerificationBasicDate;
import com.ivo.modules.coq.domain.verification.VerificationSubject;
import com.ivo.modules.coq.repository.verification.HumitureMachineRepository;
import com.ivo.modules.coq.repository.verification.OtherMachineRepository;
import com.ivo.modules.coq.repository.verification.VerificationBasicDateRepository;
import com.ivo.modules.coq.repository.verification.VerificationSubjectRepository;
import com.ivo.modules.coq.service.verification.VerificationSubjectAndMachineService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wj
 * @Date: 2019-09-20 08:44
 * @Version 1.0
 */
@Slf4j
@Service
public class VerificationSubjectAndMachineServiceImpl implements VerificationSubjectAndMachineService {

    @Autowired
    private HumitureMachineRepository humitureMachineRepository;

    @Autowired
    private OtherMachineRepository otherMachineRepository;

    @Autowired
    private VerificationBasicDateRepository verificationBasicDateRepository;

    @Autowired
    private VerificationSubjectRepository verificationSubjectRepository;

    @Override
    public List<HumitureMachine> getHumitureMachine() {
        return humitureMachineRepository.findAll();
    }

    @Override
    public HumitureMachine getHumitureMachine(Long id) {
        return humitureMachineRepository.getOne(id);
    }

    @Override
    public HumitureMachine getHumitureMachineByMachineName(String machineName) {
        return humitureMachineRepository.findFirstByMachineName(machineName);
    }

    @Override
    public List<OtherMachine> getOtherMachine() {
        return otherMachineRepository.findAll();
    }

    @Override
    public OtherMachine getOtherMachine(Long id) {
        return otherMachineRepository.getOne(id);
    }

    @Override
    public OtherMachine getOtherMachineByMachineName(String machineName) {
        return otherMachineRepository.findFirstByMachineName(machineName);
    }

    @Override
    public void addHumitureMachine(HumitureMachine humitureMachine) {
        log.info("添加温湿度机台" + humitureMachine.getMachineName());
        humitureMachineRepository.save(humitureMachine);
    }

    @Override
    public void addOtherMachine(OtherMachine otherMachine) {
        log.info("添加非温湿度机台" + otherMachine.getMachineName());
        otherMachineRepository.save(otherMachine);
        updateVerificationBasicDate(getVerificationBasicDate(2017));
    }

    @Override
    public void deleteHumitureMachine(Long id) {
        log.info("删除温湿度机台" + id);
        HumitureMachine humitureMachine = getHumitureMachine(id);
        if(humitureMachine == null) {
            String msg = "机台不存在" + id;
            log.warn(msg);
            return;
        }
        humitureMachineRepository.delete(humitureMachine);
    }

    @Override
    public void deleteOtherMachine(Long id) {
        log.info("删除非温湿度机台" + id);
        OtherMachine otherMachine = getOtherMachine(id);
        if(otherMachine == null) {
            String msg = "机台不存在" + id;
            log.warn(msg);
            return;
        }
        otherMachineRepository.delete(otherMachine);
    }

    @Override
    public VerificationBasicDate getVerificationBasicDate(int year) {
        return verificationBasicDateRepository.findFirstByYear(year);
    }

    @Override
    public List<VerificationSubject> getVerificationSubject() {
        return verificationSubjectRepository.findAll();
    }

    @Override
    public void updateVerificationBasicDate(int year, String field, Double value) {
        log.info("修改厂内验证基础数据，" + year + "," + field);
        VerificationBasicDate verificationBasicDate = getVerificationBasicDate(year);
        if(verificationBasicDate == null) {
            String msg = year + "年的验证基础数据不存在";
            log.warn(msg);
            throw new RuntimeException(msg);
        }

        if(StringUtils.equalsIgnoreCase(field, VerificationBasicDate.MAN_POWER)) {                      // 1.DL人力
            verificationBasicDate.setManPower(value);
        } else if(StringUtils.equalsIgnoreCase(field, VerificationBasicDate.MONTHLY_SALARY)) {          // 2.DL人员月薪
            verificationBasicDate.setMonthlySalary(value);
        } else if(StringUtils.equalsIgnoreCase(field, VerificationBasicDate.HUMITURE_TOTAL_POWER)) {    // 3.温湿度实验机台总能耗
            verificationBasicDate.setHumitureTotalPower(value);
        } else if(StringUtils.equalsIgnoreCase(field, VerificationBasicDate.ELECTRICITY_BILL_PRICE)) {  // 4.电费
            verificationBasicDate.setElectricityBillPrice(value);
        } else if(StringUtils.equalsIgnoreCase(field, VerificationBasicDate.VERIFICATION_QUANTITY)) {   // 5.实验数量
            verificationBasicDate.setVerificationQuantity(value);
        } else if(StringUtils.equalsIgnoreCase(field,
                VerificationBasicDate.HUMITURE_VERIFICATION_QUANTITY)) {                                // 6.温湿度类实验数量
            verificationBasicDate.setHumitureVerificationQuantity(value);
        } else if(StringUtils.equalsIgnoreCase(field,
                VerificationBasicDate.ORT_VERIFICATION_QUANTITY)) {                                     // 7.ORT
            verificationBasicDate.setOrtVerificationQuantity(value);
        } else if(StringUtils.equalsIgnoreCase(field,
                VerificationBasicDate.MATERIAL_CHANGE_VERIFICATION_QUANTITY)) {                         // 8.工程材料变更验证
            verificationBasicDate.setMaterialChangeVerificationQuantity(value);
        } else if(StringUtils.equalsIgnoreCase(field,
                VerificationBasicDate.OTHER_VERIFICATION_QUANTITY)) {                                   // 9.其他验证
            verificationBasicDate.setOtherVerificationQuantity(value);
        } else if(StringUtils.equalsIgnoreCase(field,
                VerificationBasicDate.NEW_PRODUCT_VERIFICATION_QUANTITY)) {                             // 10.新产品验证
            verificationBasicDate.setNewProductVerificationQuantity(value);
        } else if(StringUtils.equalsIgnoreCase(field, VerificationBasicDate.MAINTAIN_COST)) {           // 11.维护费用
            verificationBasicDate.setMaintainCost(value);
        }

        updateVerificationBasicDate(verificationBasicDate);
    }

    private void updateVerificationBasicDate(VerificationBasicDate verificationBasicDate) {
        computeTotalPowerHumitureMachine(verificationBasicDate);
        computeManPowerCostPer(verificationBasicDate);
        computeMaintainCostPer(verificationBasicDate);
        verificationBasicDateRepository.save(verificationBasicDate);
    }

    @Override
    public void addVerificationBasicDate(VerificationBasicDate verificationBasicDate) {
        log.info("添加厂内验证基础数据，" + verificationBasicDate.getYear());
        computeTotalPowerHumitureMachine(verificationBasicDate);
        computeManPowerCostPer(verificationBasicDate);
        computeMaintainCostPer(verificationBasicDate);
        verificationBasicDateRepository.save(verificationBasicDate);
    }

    @Override
    public void computeTotalPowerHumitureMachine(VerificationBasicDate verificationBasicDate) {
        log.info("计算温湿度机台的总能耗");
        List<HumitureMachine> machineList = getHumitureMachine();
        if(machineList == null) {
            return;
        }
        Double totalPower = null;
        for(HumitureMachine humitureMachine : machineList) {
            totalPower = DoubleUtil.sum(totalPower, humitureMachine.getPower());
        }
        verificationBasicDate.setHumitureTotalPower(totalPower);
    }

    @Override
    public void computeManPowerCostPer(VerificationBasicDate verificationBasicDate) {
        log.info("计算单片人力费用，" + verificationBasicDate.getYear());
        //计算方式： DL人力数量*月薪*12个月/全年实验数量
        Double manPower = verificationBasicDate.getManPower();
        Double monthlySalary = verificationBasicDate.getMonthlySalary();
        Double verificationQuantity = verificationBasicDate.getVerificationQuantity();

        Double costPer = DoubleUtil.multiply(manPower, 12D, monthlySalary);
        costPer = DoubleUtil.divide(costPer, verificationQuantity);
        verificationBasicDate.setManPowerCostPer(costPer);
    }

    @Override
    public void computeMaintainCostPer(VerificationBasicDate verificationBasicDate) {
        log.info("计算单片维护费用，" + verificationBasicDate.getYear());
        // 计算方式：全年机台维护费用/全年实验数量
        Double maintainCost =  verificationBasicDate.getMaintainCost();
        Double verificationQuantity = verificationBasicDate.getVerificationQuantity();
        verificationBasicDate.setMaintainCostPer(DoubleUtil.divide(maintainCost, verificationQuantity));
    }

    @Override
    public VerificationSubject getVerificationSubject(String verificationSubject) {
        return verificationSubjectRepository.findFirstByVerificationSubject(verificationSubject);
    }

    @Override
    public void updateVerificationSubject(VerificationSubject verificationSubject) {
        log.info("修改验证项目" + verificationSubject.getId() + ", " + verificationSubject.getVerificationSubject());
        computeElectricityBillPer(verificationSubject);
        verificationSubjectRepository.save(verificationSubject);
    }

    @Override
    public void addVerificationSubject(VerificationSubject verificationSubject) {
        log.info("添加验证项目，" + verificationSubject.getVerificationSubject());
        // 判断验证项目是否已存在
        if(getVerificationSubject(verificationSubject.getVerificationSubject()) != null) {
            log.error("验证项目添加失败，" + verificationSubject.getVerificationSubject() + "已存在");
            return;
        }
        // 判断非温湿度验证的关联机台是否存在
        if(StringUtils.equalsIgnoreCase(verificationSubject.getType(), VerificationSubject.TYPE_UN_HUMITURE)
                && getOtherMachineByMachineName(verificationSubject.getMachineName()) == null) {
            log.error("验证项目添加失败，" + verificationSubject.getVerificationSubject() + "验证项目的机台"
                    + verificationSubject.getMachineName() + "不存在");
            return;
        }
        // 判断温湿度验证的关联机台是否存在
        if(StringUtils.equalsIgnoreCase(verificationSubject.getType(), VerificationSubject.TYPE_HUMITURE)
                && getHumitureMachineByMachineName(verificationSubject.getMachineName()) == null) {
            log.error("验证项目添加失败，" + verificationSubject.getVerificationSubject() + "验证项目的机台"
                    + verificationSubject.getMachineName() + "不存在");
            return;
        }
        computeElectricityBillPer(verificationSubject);
        verificationSubjectRepository.save(verificationSubject);
    }

    @Override
    public void deleteVerificationSubject(long id) {
        VerificationSubject verificationSubject = verificationSubjectRepository.getOne(id);
        verificationSubjectRepository.delete(verificationSubject);
    }

    @Override
    public void computeElectricityBillPer(VerificationSubject verificationSubject) {
        log.info("计算单片电费" + verificationSubject.getVerificationSubject());
        Double costPer = null;

        VerificationBasicDate verificationBasicDate = getVerificationBasicDate(2017);
        Double electricityBillPrice = verificationBasicDate.getElectricityBillPrice();

        // 电费 = 电费价格 * 度数
        // 1度电 = 1KW*H (千瓦时)
        // 1.温湿度类  机台总能耗*24小时*365天/1000*电费/全年温湿度类实验数量
        if(StringUtils.equalsIgnoreCase(verificationSubject.getType(), VerificationSubject.TYPE_HUMITURE)) {
            Double totalPower = verificationBasicDate.getHumitureTotalPower();
            Double verificationQuantity = verificationBasicDate.getHumitureVerificationQuantity();
            costPer = DoubleUtil.divide(totalPower, 1000D);
            costPer = DoubleUtil.multiply(costPer, 24D, 365D);
            costPer = DoubleUtil.multiply(costPer, electricityBillPrice);
            costPer = DoubleUtil.divide(costPer, verificationQuantity);
        }
        // 2.非温湿度类
        else if(StringUtils.equalsIgnoreCase(verificationSubject.getType(), VerificationSubject.TYPE_UN_HUMITURE)) {
            OtherMachine otherMachine = getOtherMachineByMachineName(verificationSubject.getMachineName());
            if(otherMachine == null) {
                log.error("验证项目" + verificationSubject.getVerificationSubject() + "费温湿度机台"
                        + verificationSubject.getMachineName() + "不存在");
                return;
            }
            Double verificationTime = verificationSubject.getVerificationTime();
            Double power = otherMachine.getPower();

            costPer = DoubleUtil.divide(power, 1000D);
            costPer = DoubleUtil.multiply(costPer, verificationTime);
            costPer = DoubleUtil.multiply(costPer, electricityBillPrice);
        }

        verificationSubject.setElectricityBillPer(costPer);
    }

    public void syncElectricityBillPer() {
        List<VerificationSubject> list = getVerificationSubject();
        for (VerificationSubject verificationSubject : list) {
            computeElectricityBillPer(verificationSubject);
            verificationSubjectRepository.save(verificationSubject);
        }
    }
}
