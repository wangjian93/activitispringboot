package com.ivo.admin.coq.controller.verification;

import com.ivo.common.utils.ResultVoUtil;
import com.ivo.common.vo.ResultVo;
import com.ivo.modules.coq.domain.verification.HumitureMachine;
import com.ivo.modules.coq.domain.verification.OtherMachine;
import com.ivo.modules.coq.domain.verification.VerificationBasicDate;
import com.ivo.modules.coq.domain.verification.VerificationSubject;
import com.ivo.modules.coq.service.verification.VerificationSubjectAndMachineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 厂内验证
 * @Author: wj
 * @Date: 2019-09-20 10:57
 * @Version 1.0
 */
@Controller
@RequestMapping("/coq/verification")
@Slf4j
public class VerificationController {

    @Autowired
    private VerificationSubjectAndMachineService subjectAndMachineService;

    /**
     * 返回视图
     * @return
     */
    @GetMapping("/view")
    public String getVerificationView() {
        return "/coq/verification";
    }

    /**
     * 获取年份的厂内验证基础数据
     * @param year
     * @return
     */
    @RequestMapping("/getVerificationBasicData")
    @ResponseBody
    public ResultVo getVerificationBasicData(int year) {
        VerificationBasicDate basicDate = subjectAndMachineService.getVerificationBasicDate(year);
        return ResultVoUtil.success(basicDate);
    }

    private List<Map> convertBasicDateToMap(VerificationBasicDate basicDate) {
        List<Map> list = new ArrayList<>();
        Map manPowerMap = new HashMap();
        manPowerMap.put("year", "");
        manPowerMap.put("field", "");
        manPowerMap.put("description", "");
        manPowerMap.put("value", "");
        manPowerMap.put("", "");




        // 基础数据常量
//        public static final String MAN_POWER = "manPower";
//        public static final String MONTHLY_SALARY = "monthlySalary";
//        public static final String HUMITURE_TOTAL_POWER = "humitureTotalPower";
//        public static final String ELECTRICITY_BILL_PRICE = "electricityBillPrice";
//        public static final String VERIFICATION_QUANTITY = "verificationQuantity";
//        public static final String HUMITURE_VERIFICATION_QUANTITY = "humitureVerificationQuantity";
//        public static final String ORT_VERIFICATION_QUANTITY = "ortVerificationQuantity";
//        public static final String MATERIAL_CHANGE_VERIFICATION_QUANTITY = "materialChangeVerificationQuantity";
//        public static final String OTHER_VERIFICATION_QUANTITY = "otherVerificationQuantity";
//        public static final String NEW_PRODUCT_VERIFICATION_QUANTITY = "newProductVerificationQuantity";
//        public static final String MAINTAIN_COST = "maintainCost";
return null;
    }

    /**
     * 添加基础数据
     * @param verificationBasicDate
     * @return
     */
    @RequestMapping("/addVerificationBasicData")
    @ResponseBody
    public ResultVo addVerificationBasicData(VerificationBasicDate verificationBasicDate) {
        subjectAndMachineService.addVerificationBasicDate(verificationBasicDate);
        return ResultVoUtil.success();
    }

    /**
     * 修改基础数据
     * @param year
     * @param field
     * @param value
     * @return
     */
    @RequestMapping("/updateVerificationBasicData")
    @ResponseBody
    public ResultVo updateVerificationBasicData(int year, String field, Double value) {
        subjectAndMachineService.updateVerificationBasicDate(year, field, value);
        return ResultVoUtil.success();
    }

    /**
     * 获取温湿度机台数据
     * @return
     */
    @RequestMapping("/getHumitureMachine")
    @ResponseBody
    public ResultVo getHumitureMachine() {
        List<HumitureMachine> list = subjectAndMachineService.getHumitureMachine();
        return ResultVoUtil.success(list);
    }

    /**
     * 添加温湿度机台
     * @param humitureMachine
     * @return
     */
    @RequestMapping("/addHumitureMachine")
    @ResponseBody
    public ResultVo addHumitureMachine(HumitureMachine humitureMachine) {
        subjectAndMachineService.addHumitureMachine(humitureMachine);
        return ResultVoUtil.success();
    }

    /**
     * 删除温湿度机台
     * @param id
     * @return
     */
    @RequestMapping("/deleteHumitureMachine")
    @ResponseBody
    public ResultVo deleteHumitureMachine(long id) {
        subjectAndMachineService.deleteHumitureMachine(id);
        return ResultVoUtil.success();
    }

    /**
     * 获取非温湿度机台数据
     * @return
     */
    @RequestMapping("/getOtherMachine")
    @ResponseBody
    public ResultVo getOtherMachine() {
        List<OtherMachine> list = subjectAndMachineService.getOtherMachine();
        return ResultVoUtil.success(list);
    }

    /**
     * 添加非温湿度机台
     * @param otherMachine
     * @return
     */
    @RequestMapping("/addOtherMachine")
    @ResponseBody
    public ResultVo addOtherMachine(OtherMachine otherMachine) {
        subjectAndMachineService.addOtherMachine(otherMachine);
        return ResultVoUtil.success();
    }

    /**
     * 删除非温湿度机台
     * @param id
     * @return
     */
    @RequestMapping("/deleteOtherMachine")
    @ResponseBody
    public ResultVo deleteOtherMachine(long id) {
        subjectAndMachineService.deleteHumitureMachine(id);
        return ResultVoUtil.success();
    }

    /**
     * 获取厂内的验证项目数据
     * @return
     */
    @RequestMapping("/getVerificationSubject")
    @ResponseBody
    public ResultVo getVerificationSubject() {
        List<VerificationSubject> list = subjectAndMachineService.getVerificationSubject();
        return ResultVoUtil.success(list);
    }

    /**
     * 添加验证项目
     * @param verificationSubject
     * @return
     */
    @RequestMapping("/addVerificationSubject")
    @ResponseBody
    public ResultVo addVerificationSubject(VerificationSubject verificationSubject) {
        subjectAndMachineService.addVerificationSubject(verificationSubject);
        return ResultVoUtil.success();
    }

    /**
     * 修改验证项目
     * @param verificationSubject
     * @return
     */
    @RequestMapping("/updateVerificationSubject")
    @ResponseBody
    public ResultVo updateVerificationSubject(VerificationSubject verificationSubject) {
        subjectAndMachineService.updateVerificationSubject(verificationSubject);
        return ResultVoUtil.success();
    }

    /**
     * 删除验证项目
     * @param id
     * @return
     */
    @RequestMapping("/deleteVerificationSubject")
    @ResponseBody
    public ResultVo deleteVerificationSubject(long id) {
        subjectAndMachineService.deleteVerificationSubject(id);
        return ResultVoUtil.success();
    }
}
