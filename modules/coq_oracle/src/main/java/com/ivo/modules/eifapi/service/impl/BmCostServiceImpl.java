package com.ivo.modules.eifapi.service.impl;

import com.ivo.modules.eifapi.repository.BmCostRepository;
import com.ivo.modules.eifapi.service.BmCostService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: wj
 * @Date: 2019-08-19 08:44
 * @Version 1.0
 */
@Service
public class BmCostServiceImpl implements BmCostService {

    @Autowired
    private BmCostRepository bmCostRepository;

    @Override
    public Double getGudingzichan(Date fromDate, Date toDate, String project) {
        return bmCostRepository.test1(fromDate, toDate, project);
    }

    @Override
    public Double getQitafeiyong(Date fromDate, Date toDate, String project) {
        return bmCostRepository.test2(fromDate, toDate, project);
    }

    @Override
    public Double getWaibaobohua(Date fromDate, Date toDate, String project) {
        return bmCostRepository.test3(fromDate, toDate, project);
    }

    @Override
    public Double getJiance(Date fromDate, Date toDate, String project) {
        return bmCostRepository.test4(fromDate, toDate, project);
    }


    @Override
    public List<Map<String, Object>> getToolCapital(Date fromDate, Date toDate, String project) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        project = project.replace(" ", "-") + "%";

        return bmCostRepository.getToolCapital(fromDate, toDate, project);
    }

    @Override
    public List<Map<String, Object>> getToolOther(Date fromDate, Date toDate, String project) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        project = project.replace(" ", "-") + "%";
        return bmCostRepository.getToolOther(fromDate, toDate, project);
    }

    @Override
    public List<Map<String, Object>> getDirectMaterial(Date fromDate, Date toDate, String project) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        project = project.replace(" ", "-") + "%";
        return bmCostRepository.getDirectMaterial(fromDate, toDate, project);
    }

    @Override
    public List<Map<String, Object>> getValidation(Date fromDate, Date toDate, String project) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        project = project.replace(" ", "-") + "%";
        return bmCostRepository.getValidation(fromDate, toDate, project);
    }
}
