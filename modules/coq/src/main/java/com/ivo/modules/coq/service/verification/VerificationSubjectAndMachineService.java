package com.ivo.modules.coq.service.verification;

import com.ivo.modules.coq.domain.verification.HumitureMachine;
import com.ivo.modules.coq.domain.verification.OtherMachine;
import com.ivo.modules.coq.domain.verification.VerificationBasicDate;
import com.ivo.modules.coq.domain.verification.VerificationSubject;

import java.util.List;

/**
 * 验证项目和机台Service
 * @Author: wj
 * @Date: 2019-09-19 15:15
 * @Version 1.0
 */
public interface VerificationSubjectAndMachineService {

    /**
     * 获取温湿度机台
     * @return
     */
    List<HumitureMachine> getHumitureMachine();

    /**
     * 获取温湿度机台
     * @param
     * @return
     */
    HumitureMachine getHumitureMachine(Long id);

    /**
     * 根据机台名获取温湿度机台
     * @param machineName
     * @return
     */
    HumitureMachine getHumitureMachineByMachineName(String machineName);


    /**
     * 获取非温湿度机台
     * @return
     */
    List<OtherMachine> getOtherMachine();

    /**
     * 获取非温湿度机台
     * @param id
     * @return
     */
    OtherMachine getOtherMachine(Long id);

    /**
     * 根据机台名获取非温湿度机台
     * @param machineName
     * @return
     */
    OtherMachine getOtherMachineByMachineName(String machineName);


    /**
     * 添加温湿度机台
     * @param humitureMachine
     */
    void addHumitureMachine(HumitureMachine humitureMachine);

    /**
     * 添加非温湿度机台
     * @param otherMachine
     */
    void addOtherMachine(OtherMachine otherMachine);

    /**
     * 删除温湿度机台
     * @param
     */
    void deleteHumitureMachine(Long id);

    /**
     * 删除非温湿度机台
     * @param
     */
    void deleteOtherMachine(Long id);

    /**
     * 获取year年份的基础数据
     * @param year
     * @return
     */
    VerificationBasicDate getVerificationBasicDate(int year);

    /**
     * 更新基础数据
     * @param year 年份
     * @param field 更新字段
     * @param value 更新的数据
     */
    void updateVerificationBasicDate(int year, String field, Double value);

    /**
     * 添加基础数据
     * @param verificationBasicDate
     */
    void addVerificationBasicDate(VerificationBasicDate verificationBasicDate);

    /**
     * 计算非温湿度实验机台总能耗
     * @return
     */
    void computeTotalPowerHumitureMachine(VerificationBasicDate verificationBasicDate);

    /**
     * 计算单片人力费用
     * @param verificationBasicDate
     * @return
     */
    void computeManPowerCostPer(VerificationBasicDate verificationBasicDate);

    /**
     * 计算单片维护费用
     * @param verificationBasicDate
     * @return
     */
    void computeMaintainCostPer(VerificationBasicDate verificationBasicDate);

    /**
     * 获取验证项目
     * @param verificationSubject
     * @return
     */
    VerificationSubject getVerificationSubject(String verificationSubject);

    List<VerificationSubject> getVerificationSubject();

    /**
     * 更新验证项目
     * @param verificationSubject
     */
    void updateVerificationSubject(VerificationSubject verificationSubject);

    /**
     * 添加验证项目
     * @param verificationSubject
     */
    void addVerificationSubject(VerificationSubject verificationSubject);

    /**
     * 删除验证项目
     * @param id
     */
    void deleteVerificationSubject(long id);


    /**
     * 计算验证项目的单片电费
     * @param verificationSubject
     */
    void computeElectricityBillPer(VerificationSubject verificationSubject);

    void syncElectricityBillPer();

}
