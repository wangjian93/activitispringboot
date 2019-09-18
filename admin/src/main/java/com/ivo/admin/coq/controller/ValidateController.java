package com.ivo.admin.coq.controller;

import com.ivo.common.utils.ResultVoUtil;
import com.ivo.common.vo.ResultVo;
import com.ivo.modules.coq.domain.validate.ValidateBase;
import com.ivo.modules.coq.domain.validate.ValidateMachine;
import com.ivo.modules.coq.domain.validate.ValidateSubject;
import com.ivo.modules.coq.service.ValidateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: wj
 * @Date: 2019-09-08 21:49
 * @Version 1.0
 */
@Controller
@RequestMapping("/cost")
@Slf4j
public class ValidateController {

    @Autowired
    private ValidateService validateService;

    @GetMapping("/validate")
    public String validateView() {
        return "/coq/validate";
    }

    @PostMapping("/getValidateBase")
    @ResponseBody
    public ResultVo getValidateBase(@RequestParam(defaultValue = "0", required = false) int year) {
        if(year == 0) {
            year = 2017;
        }
        List<ValidateBase> list = validateService.getValidateBase(year);
        ResultVo resultVo = ResultVoUtil.success(list);
        resultVo.setCount(list.size());
        return resultVo;
    }

    @PostMapping("/getValidateSubject")
    @ResponseBody
    public ResultVo getValidateSubject(@RequestParam(defaultValue = "0", required = false) int year) {
        if(year == 0) {
            year = 2017;
        }
        List<ValidateSubject> list = validateService.getValidateSubject(year);
        ResultVo resultVo = ResultVoUtil.success(list);
        resultVo.setCount(list.size());
        return resultVo;
    }


    @PostMapping("/getValidateMachine")
    @ResponseBody
    public ResultVo getValidateMachine() {
        List<ValidateMachine> list = validateService.getValidateMachine();
        ResultVo resultVo = ResultVoUtil.success(list);
        resultVo.setCount(list.size());
        return resultVo;
    }

    @PostMapping("/updateValidateBase")
    @ResponseBody
    public ResultVo updateValidateBase(int year, String name, Double number) {
        validateService.updateValidateBase(year, name, number);
        return ResultVoUtil.success();

    }

    @PostMapping("/deleteValidateMachine")
    @ResponseBody
    public ResultVo deleteValidateMachine(long id) {
        validateService.deleteValidateMachineById(id);
        return ResultVoUtil.success();
    }

    @PostMapping("/addValidateMachine")
    @ResponseBody
    public ResultVo addValidateMachine(ValidateMachine validateMachine) {
        validateService.saveValidateMachine(validateMachine);
        return ResultVoUtil.success();
    }

}
