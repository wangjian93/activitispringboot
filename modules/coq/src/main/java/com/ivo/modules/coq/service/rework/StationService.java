package com.ivo.modules.coq.service.rework;

import com.ivo.modules.coq.domain.rework.Station;
import com.ivo.modules.coq.domain.rework.StationRework;

import java.util.List;

/**
 * 机种站点service
 * @Author: wj
 * @Date: 2019-10-13 23:21
 * @Version 1.0
 */
public interface StationService {

    /**
     * 获取机种的站点信息
     * @param project
     * @return
     */
    List<Station> getStations(String project);

    /**
     * 批量保存站点
     * @param stationList
     */
    void saveStations(List<Station> stationList);

    /**
     * 批量删除机种的站点
     * @param stationList
     */
    void deleteStations(List<Station> stationList);

    /**
     * 获取机种的站点信息
     * @param project
     * @return
     */
    List<StationRework> getStationReworks(String project);

    /**
     * 批量保存站点
     * @param stationReworkList
     */
    void saveStationReworks(List<StationRework> stationReworkList);

    /**
     * 批量删除机种的站点
     * @param stationReworkList
     */
    void deleteStationReworks(List<StationRework> stationReworkList);
}
