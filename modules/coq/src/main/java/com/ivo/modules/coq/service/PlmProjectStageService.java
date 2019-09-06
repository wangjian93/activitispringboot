package com.ivo.modules.coq.service;

import com.ivo.modules.coq.domain.PlmProjectStage;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 机种阶段service
 * @Author: wj
 * @Date: 2019-08-20 14:40
 * @Version 1.0
 */
public interface PlmProjectStageService {

    /**
     * @param project
     * @return
     */
    List<PlmProjectStage> getPlmStageList(String project);

    /**
     *
     * @param project
     * @param stage
     * @return
     */
    List<PlmProjectStage> getPlmStageList(String project, String stage);

    /**
     *
     * @param project
     * @param stage
     * @param process
     * @return
     */
    PlmProjectStage getPlmStage(String project, String stage, String process);

    /**
     * 同步plm机种的阶段信息
     * @param project
     */
    List<PlmProjectStage> syncStageFromPlm(String project);

    /**
     * 获取机种阶段名称
     * @param project
     * @return
     */
    List<String> getStages(String project);
}
