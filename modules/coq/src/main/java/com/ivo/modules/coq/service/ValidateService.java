package com.ivo.modules.coq.service;

import com.ivo.modules.coq.domain.QmsValidate;
import com.ivo.modules.coq.domain.validate.ValidateBase;
import com.ivo.modules.coq.domain.validate.ValidateSubject;
import com.ivo.modules.coq.domain.validate.ValidateMachine;

import java.util.List;

/**
 * @Author: wj
 * @Date: 2019-09-08 18:08
 * @Version 1.0
 */
public interface ValidateService {

    void saveValidateBase(ValidateBase validateBase);

    void saveValidateSubject(ValidateSubject validateSubject);

    void saveValidateMachine(ValidateMachine validateMachine);

    List<ValidateBase> getValidateBase(int year);

    List<ValidateSubject> getValidateSubject(int year);

    List<ValidateMachine> getValidateMachine();

    void syncValidateSubject(int year);

    void syncValidateBase(int year);

    List<QmsValidate> getQmsValidate(String project);

    ValidateBase getValidateBase(int year, String name);

    void updateValidateBase(int year, String name, Double number);

    void deleteValidateMachineById(long id);
}
