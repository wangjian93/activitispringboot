package com.ivo.modules.eifapi.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: wj
 * @Date: 2019-08-19 08:41
 * @Version 1.0
 */
public interface BmCostService {

    Double getGudingzichan(Date fromDate, Date toDate, String project);

    Double getQitafeiyong(Date fromDate, Date toDate, String project);

    Double getWaibaobohua(Date fromDate, Date toDate, String project);

    Double getJiance(Date fromDate, Date toDate, String project);



    List<Map<String, Object>> getToolCapital(Date fromDate, Date toDate, String project);

    List<Map<String, Object>> getToolOther(Date fromDate, Date toDate, String project);

    List<Map<String, Object>> getDirectMaterial(Date fromDate, Date toDate, String project);

    List<Map<String, Object>> getValidation(Date fromDate, Date toDate, String project);

}
