package com.ivo.modules.coq.service.impl;

import com.ivo.modules.coq.domain.PlmProjectSchedule;
import com.ivo.modules.coq.enums.StageTypeEnum;
import com.ivo.modules.coq.repository.PlmProjectScheduleRepository;
import com.ivo.modules.coq.rest.RestService;
import com.ivo.modules.coq.service.PlmProjectScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: wj
 * @Date: 2019-08-21 11:08
 * @Version 1.0
 */
@Service
@Slf4j
public class PlmProjectScheduleServiceImpl implements PlmProjectScheduleService {

    @Autowired
    private PlmProjectScheduleRepository repository;

    @Autowired
    private RestService restService;

    @Override
    public List<PlmProjectSchedule> getPlmScheduleList(String project) {
        return repository.findByProject(project);
    }

    @Override
    public PlmProjectSchedule getPlmSchedule(String project, String stage) {
        return repository.findDistinctByProjectAndStage(project, stage);
    }

    @Override
    public List<PlmProjectSchedule> syncScheduleFromPlm(String project) {
        // 数据接口获取数据
        List<Map<String, String>> dataList = restService.getScheduleFromPlm(project);
        // 删除原有的数据
        deletePlmSchedule(getPlmScheduleList(project));

        // 同步新数据
        if(dataList == null) {
            return null;
        }
        List<PlmProjectSchedule> newList = new ArrayList<>();
        dataList.forEach(map -> {
            PlmProjectSchedule plmProjectSchedule = new PlmProjectSchedule(project);
            analyseSchedule(plmProjectSchedule, map);
            newList.add(plmProjectSchedule);
        });
        savePlmSchedule(newList);
        return getPlmScheduleList(project);
    }

    private  void deletePlmSchedule(List<PlmProjectSchedule> list) {
        repository.deleteAll(list);
    }

    private void savePlmSchedule(List<PlmProjectSchedule> list) {
        repository.saveAll(list);
    }

    private void analyseSchedule(PlmProjectSchedule plmProjectSchedule, Map<String, String> map) {
        String stage = map.get("stage").toUpperCase();
        // 防止阶段字符串不统一
        if(StringUtils.contains(stage, StageTypeEnum.NPRB.getStage())) {
            stage = StageTypeEnum.NPRB.getStage();
        } else if(StringUtils.contains(stage, StageTypeEnum.DESIGN.getStage())) {
            stage = StageTypeEnum.DESIGN.getStage();
        } else if(StringUtils.contains(stage, StageTypeEnum.DVT.getStage())) {
            stage = StageTypeEnum.DVT.getStage();
        } else if(StringUtils.contains(stage, StageTypeEnum.EVT.getStage())) {
            stage = StageTypeEnum.EVT.getStage();
        } else if(StringUtils.contains(stage, StageTypeEnum.PVT.getStage())) {
            stage = StageTypeEnum.PVT.getStage();
        }

        plmProjectSchedule.setStage(stage);
        plmProjectSchedule.setStatus(map.get("status"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            plmProjectSchedule.setDate(format.parse(map.get("date")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
