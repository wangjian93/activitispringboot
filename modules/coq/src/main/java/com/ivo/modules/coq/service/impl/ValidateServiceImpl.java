package com.ivo.modules.coq.service.impl;

import com.ivo.modules.coq.cost.DoubleUtil;
import com.ivo.modules.coq.domain.QmsValidate;
import com.ivo.modules.coq.domain.validate.ValidateBase;
import com.ivo.modules.coq.domain.validate.ValidateSubject;
import com.ivo.modules.coq.domain.validate.ValidateMachine;
import com.ivo.modules.coq.repository.QmsValidateRepository;
import com.ivo.modules.coq.repository.validate.ValidateBaseRepository;
import com.ivo.modules.coq.repository.validate.ValidateSubjectRepository;
import com.ivo.modules.coq.repository.validate.ValidateMachineRepository;
import com.ivo.modules.coq.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wj
 * @Date: 2019-09-08 18:37
 * @Version 1.0
 */
@Service
public class ValidateServiceImpl implements ValidateService {

    @Autowired
    private ValidateBaseRepository baseRepository;

    @Autowired
    private ValidateSubjectRepository subjectRepository;

    @Autowired
    private ValidateMachineRepository machineRepository;

    @Autowired
    private QmsValidateRepository qmsValidateRepository;

    @Override
    public void saveValidateBase(ValidateBase validateBase) {
        baseRepository.save(validateBase);
    }

    @Override
    public void saveValidateSubject(ValidateSubject validateSubject) {
        subjectRepository.save(validateSubject);
    }

    @Override
    public void saveValidateMachine(ValidateMachine validateMachine) {
        machineRepository.save(validateMachine);
    }

    @Override
    public List<ValidateBase> getValidateBase(int year) {
        return baseRepository.findByOrderBySortAsc();
    }

    @Override
    public List<ValidateSubject> getValidateSubject(int year) {
        return subjectRepository.findAll();
    }

    @Override
    public List<ValidateMachine> getValidateMachine() {
        return machineRepository.findAll();
    }

    private List<ValidateMachine> getValidateMachineForHumiture() {
        return machineRepository.findByType(ValidateMachine.TYPE_HUMITURE);
    }


    @Override
    public void syncValidateSubject(int year) {
        deleteValidateSubject(getValidateSubject(year));

        ValidateSubject validateSubject1 = new ValidateSubject(year, ValidateSubject.SUBJECT_MAINTENANCE_COST);
        Double MAINTENANCE_COST = getValidateBase(year, ValidateBase.NAME_MAINTENANCE_COST).getNumber();
        Double EXPERIMENTAL_QUANTITY = getValidateBase(year, ValidateBase.NAME_EXPERIMENTAL_QUANTITY).getNumber();

        validateSubject1.setAmount(DoubleUtil.divide(MAINTENANCE_COST, EXPERIMENTAL_QUANTITY));
        validateSubject1.setRemark("");
        validateSubject1.setFormula(ValidateSubject.FORMULA_MAINTENANCE_COST);
        validateSubject1.setCurrency("RMB");


        ValidateSubject validateSubject2 = new ValidateSubject(year, ValidateSubject.SUBJECT_MANPOWER);
        Double DL_MANPOWER = getValidateBase(year, ValidateBase.NAME_DL_MANPOWER).getNumber();
        Double DL_SALARY = getValidateBase(year, ValidateBase.NAME_DL_SALARY).getNumber();

        validateSubject2.setAmount(DoubleUtil.divide(DoubleUtil.multiply(DL_MANPOWER, DL_SALARY, 12D), EXPERIMENTAL_QUANTITY));
        validateSubject2.setRemark("");
        validateSubject2.setFormula(ValidateSubject.FORMULA_MANPOWER);
        validateSubject2.setCurrency("RMB");


        ValidateSubject validateSubject3 = new ValidateSubject(year, ValidateSubject.SUBJECT_HUMITURE_ENERGY_CONSUMPTION);
        Double HUMITURE_ENERGY_CONSUMPTION = getValidateBase(year, ValidateBase.NAME_HUMITURE_ENERGY_CONSUMPTION).getNumber();
        Double ELECTRIC_CHARGE = getValidateBase(year, ValidateBase.NAME_ELECTRIC_CHARGE).getNumber();
        Double HUMITURE_EXPERIMENTAL_QUANTITY = getValidateBase(year, ValidateBase.NAME_HUMITURE_EXPERIMENTAL_QUANTITY).getNumber();

        Double d = DoubleUtil.divide(
                DoubleUtil.multiply(
                        DoubleUtil.divide(
                                DoubleUtil.multiply(HUMITURE_ENERGY_CONSUMPTION, 24D, 365D),
                                1000D),
                        ELECTRIC_CHARGE),
                HUMITURE_EXPERIMENTAL_QUANTITY);
        validateSubject3.setAmount(d);
        validateSubject3.setRemark("");
        validateSubject3.setFormula(ValidateSubject.FORMULA_HUMITURE_ENERGY_CONSUMPTION);
        validateSubject3.setCurrency("RMB");

        subjectRepository.save(validateSubject1);
        subjectRepository.save(validateSubject2);
        subjectRepository.save(validateSubject3);
    }

    @Override
    public void syncValidateBase(int year) {

        List<ValidateBase> list = new ArrayList<>();

        ValidateBase validateBase = new ValidateBase();
        validateBase.setYear(year);
        validateBase.setName(ValidateBase.NAME_DL_MANPOWER);
        validateBase.setDescription("实验室DL人力（IVO+IVE）");
        validateBase.setNumber(28.0);
        validateBase.setUnit("人");
        list.add(validateBase);

        ValidateBase validateBase1 = new ValidateBase();
        validateBase1.setYear(year);
        validateBase1.setName(ValidateBase.NAME_DL_SALARY);
        validateBase1.setDescription("实验室DL人员月薪基数");
        validateBase1.setNumber(3000.0);
        validateBase1.setUnit("RMB");
        list.add(validateBase1);

        ValidateBase validateBase2 = new ValidateBase();
        validateBase2.setYear(year);
        validateBase2.setName(ValidateBase.NAME_HUMITURE_ENERGY_CONSUMPTION);
        validateBase2.setDescription("温湿度实验机台总能耗");
        Double n = null;
        for(ValidateMachine machine :getValidateMachineForHumiture()) {
            n = DoubleUtil.sum(n, machine.getEnergyConsumption());
        }
        validateBase2.setNumber(n);
        validateBase2.setUnit("W");
        list.add(validateBase2);

        ValidateBase validateBase3 = new ValidateBase();
        validateBase3.setYear(year);
        validateBase3.setName(ValidateBase.NAME_ELECTRIC_CHARGE);
        validateBase3.setDescription("电费");
        validateBase3.setNumber(0.62);
        validateBase3.setUnit("RMB");
        list.add(validateBase3);

        ValidateBase validateBase4 = new ValidateBase();
        validateBase4.setYear(year);
        validateBase4.setName(ValidateBase.NAME_EXPERIMENTAL_QUANTITY);
        validateBase4.setDescription("2017年全年实验数量");
        validateBase4.setNumber(254285D);
        validateBase4.setUnit("PCS");
        list.add(validateBase4);

        ValidateBase validateBase5 = new ValidateBase();
        validateBase5.setYear(year);
        validateBase5.setName(ValidateBase.NAME_HUMITURE_EXPERIMENTAL_QUANTITY);
        validateBase5.setDescription("2017年全年温湿度类实验数量");
        validateBase5.setNumber(136167d);
        validateBase5.setUnit("PCS");
        list.add(validateBase5);

        ValidateBase validateBase6 = new ValidateBase();
        validateBase6.setYear(2017);
        validateBase6.setName(ValidateBase.NAME_ORT);
        validateBase6.setDescription("2017年全年ORT验证");
        validateBase6.setNumber(92425d);
        validateBase6.setUnit("PCS");
        list.add(validateBase6);

        ValidateBase validateBase7 = new ValidateBase();
        validateBase7.setYear(year);
        validateBase7.setName(ValidateBase.NAME_ENGINEERING_MATERIAL_CHANGE);
        validateBase7.setDescription("2017年全年工程材料变更验证");
        validateBase7.setNumber(44760d);
        validateBase7.setUnit("PCS");
        list.add(validateBase7);

        ValidateBase validateBase8 = new ValidateBase();
        validateBase8.setYear(2017);
        validateBase8.setName(ValidateBase.NAME_OTHER);
        validateBase8.setDescription("2017年全年其他验证");
        validateBase8.setNumber(42107d);
        validateBase8.setUnit("PCS");
        list.add(validateBase8);

        ValidateBase validateBase9 = new ValidateBase();
        validateBase9.setYear(year);
        validateBase9.setName(ValidateBase.NAME_NEW_PRODUCT);
        validateBase9.setDescription("2017年全年新产品验证");
        validateBase9.setNumber(74993D);
        validateBase9.setUnit("PCS");
        list.add(validateBase9);

        ValidateBase validateBase10 = new ValidateBase();
        validateBase10.setYear(year);
        validateBase10.setName(ValidateBase.NAME_MAINTENANCE_COST);
        validateBase10.setDescription("2017年全年机台维护费用");
        validateBase10.setNumber(336960D);
        validateBase10.setUnit("PCS");
        list.add(validateBase10);

        list.forEach(base -> {
            saveValidateBase(base);
        });
    }

    public ValidateBase getValidateBase(int year, String name) {
        return baseRepository.findDistinctByYearAndName(year, name);
    }

    private void deleteValidateBase(List<ValidateBase> list) {
        baseRepository.deleteAll(list);
    }

    private void deleteValidateSubject(List<ValidateSubject> list) {
        subjectRepository.deleteAll(list);
    }

    @Override
    public List<QmsValidate> getQmsValidate(String project) {
        return qmsValidateRepository.findByProject(project);
    }

    @Override
    public void updateValidateBase(int year, String name, Double number) {
        ValidateBase validateBase = getValidateBase(year, name);
        ValidateBase newValidateBase = new ValidateBase(validateBase.getYear(), validateBase.getName());
        newValidateBase.setNumber(number);
        newValidateBase.setUnit(validateBase.getUnit());
        newValidateBase.setDescription(validateBase.getDescription());
        newValidateBase.setSort(validateBase.getSort());

        List<ValidateBase> list = new ArrayList<>();
        list.add(validateBase);
        deleteValidateBase(list);
        saveValidateBase(newValidateBase);
        syncValidateSubject(year);
    }

    @Override
    public void deleteValidateMachineById(long id) {
        machineRepository.deleteById(id);
    }
}
