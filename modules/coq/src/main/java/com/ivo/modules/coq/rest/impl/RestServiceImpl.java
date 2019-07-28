package com.ivo.modules.coq.rest.impl;

import com.ivo.modules.coq.rest.RestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: wj
 * @Date: 2019-07-25 10:57
 * @Version 1.0
 */
@Service
@Slf4j
public class RestServiceImpl implements RestService {

    private static RestTemplate restTemplate = new RestTemplate();
    private static String url = "http://10.20.51.149:8080/im/restful/sampleInter.do";

    /**
     * PLM接口-1
     * 根据机种名从PLM系统获取机种的阶段信息
     * @param project 机种
     * @return
     */
    @Override
    public List<Map<String, String>> getProjectStage(String project) {
//        List<Map<String, String>> list = restTemplate.getForObject(url + "?name=" + project, List.class);
//        if(list == null) {
//            list = new ArrayList<>();
//        }
        List<Map<String, String>> list = new ArrayList<>();
        for(int i=1; i<4; i++) {
            Map<String, String> map1 = new HashMap<>();
            map1.put("Stage", "DVT-" + i);
            map1.put("Process", "Array");
            map1.put("inQuantity", "30");
            map1.put("outQuantity", "20");
            map1.put("inDate", "2019-07-27 00:00:00");
            map1.put("outDate", "2019-07-27 00:00:00");
            map1.put("complete", "true");
            map1.put("orderNumber", "EE190800067");
            map1.put("units", "ps");

            Map<String, String> map2 = new HashMap<>();
            map2.put("Stage", "DVT-" + i);
            map2.put("Process", "Module");
            map2.put("inQuantity", "30");
            map2.put("outQuantity", "20");
            map2.put("inDate", "2019-07-27 00:00:00");
            map2.put("outDate", "2019-07-27 00:00:00");
            map2.put("complete", "true");
            map2.put("orderNumber", "EE190800067");
            map2.put("units", "ps");

            Map<String, String> map3 = new HashMap<>();
            map3.put("Stage", "DVT-" + i);
            map3.put("Process", "Cell");
            map3.put("inQuantity", "30");
            map3.put("outQuantity", "20");
            map3.put("inDate", "2019-07-27 00:00:00");
            map3.put("outDate", "2019-07-27 00:00:00");
            map3.put("complete", "true");
            map3.put("orderNumber", "EE190800067");
            map3.put("units", "ps");

            list.add(map1);
            list.add(map2);
            list.add(map3);
        }
        return list;
    }
}
