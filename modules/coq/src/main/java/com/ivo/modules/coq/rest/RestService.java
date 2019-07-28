package com.ivo.modules.coq.rest;

import java.util.List;
import java.util.Map;

/**
 * 访问外系统接口服务
 * @Author: wj
 * @Date: 2019-07-25 10:55
 * @Version 1.0
 */
public interface RestService {

    /**
     * PLM接口-1
     * 根据机种从PLM系统获取机种的阶段信息
     * @param project 机种
     */
    List<Map<String, String>> getProjectStage(String project);
}
