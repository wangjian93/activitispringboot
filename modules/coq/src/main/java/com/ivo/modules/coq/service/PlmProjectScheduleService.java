package com.ivo.modules.coq.service;

import com.ivo.modules.coq.domain.PlmProjectSchedule;

import java.util.List;

/**
 * 机种进度service
 * @Author: wj
 * @Date: 2019-08-21 11:08
 * @Version 1.0
 */
public interface PlmProjectScheduleService {

    /**
     * @param project
     * @return
     */
    List<PlmProjectSchedule> getPlmScheduleList(String project);

    /**
     *
     * @param project
     * @param stage
     * @return
     */
    PlmProjectSchedule getPlmSchedule(String project, String stage);

    /**
     * 同步plm机种的各阶段进度
     * @param project
     */
    List<PlmProjectSchedule> syncScheduleFromPlm(String project);
}
