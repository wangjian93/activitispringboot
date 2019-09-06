package com.ivo.modules.coq.service.impl;

import com.ivo.modules.coq.domain.PlmProjectStage;
import com.ivo.modules.coq.enums.StageTypeEnum;
import com.ivo.modules.coq.repository.PlmProjectStageRepository;
import com.ivo.modules.coq.rest.RestService;
import com.ivo.modules.coq.service.PlmProjectStageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: wj
 * @Date: 2019-08-20 16:47
 * @Version 1.0
 */
@Service
@Slf4j
public class PlmProjectStageServiceImpl implements PlmProjectStageService {

    @Autowired
    private PlmProjectStageRepository repository;

    @Autowired
    private RestService restService;

    @Override
    public List<PlmProjectStage> getPlmStageList(String project) {
        return repository.findByProject(project);
    }

    @Override
    public List<PlmProjectStage> getPlmStageList(String project, String stage) {
        return repository.findByProjectAndStage(project, stage);
    }

    @Override
    public PlmProjectStage getPlmStage(String project, String stage, String process) {
        return repository.findDistinctByProjectAndStageAndProcess(project, stage, process);
    }

    @Override
    public List<PlmProjectStage> syncStageFromPlm(String project) {
        // 接口获取数据
        List<Map<String, String>> dataList = restService.getStageFromPlm(project);
        // 删除原有的数据
        deletePlmStage( getPlmStageList(project));

        // 同步新数据
        if(dataList == null) {
            return null;
        }
        List<PlmProjectStage> newList = new ArrayList<>();
        dataList.forEach(map -> {
            PlmProjectStage plmProjectStage = new PlmProjectStage(project);
            analyseStage(plmProjectStage, map);
            newList.add(plmProjectStage);
        });

        // PLM中没有NPRB和Design阶段，追加
        newList.add(new PlmProjectStage(project, StageTypeEnum.NPRB.getStage()));
        newList.add(new PlmProjectStage(project, StageTypeEnum.DESIGN.getStage()));
        Collections.sort(newList);
        savePlmStage(newList);
        return getPlmStageList(project);
    }

    private void analyseStage(PlmProjectStage plmProjectStage, Map<String, String> map) {
        plmProjectStage.setStage(map.get("Stage").toUpperCase());
        plmProjectStage.setProcess(map.get("Process"));
        plmProjectStage.setInQuantity(Double.valueOf(map.get("inQuantity")));
        plmProjectStage.setOutQuantity(Double.valueOf(map.get("outQuantity")));
        plmProjectStage.setUnits(map.get("units"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            plmProjectStage.setInDate(format.parse(map.get("inDate")));
            plmProjectStage.setOutDate(format.parse(map.get("inDate")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        plmProjectStage.setComplete(Boolean.valueOf(map.get("complete")));
        plmProjectStage.setEdOrEeOrOrderNumber(map.get("orderNumber"));
    }

    private void savePlmStage(List<PlmProjectStage> list) {
        repository.saveAll(list);
    }

    private void deletePlmStage(List<PlmProjectStage> list) {
        repository.deleteAll(list);
    }

    @Override
    public List<String> getStages(String project) {
        List<String> stages = new ArrayList<>();
        getPlmStageList(project).forEach(plmProjectStage -> {
            if(!stages.contains(plmProjectStage.getStage())) {
                stages.add(plmProjectStage.getStage());
            }
        });
        return stages;
    }
}
