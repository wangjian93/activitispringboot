package com.ivo.modules.coq.rest.impl;

import com.ivo.modules.coq.rest.RestService;
import com.ivo.modules.eifapi.service.BmCostService;
import com.ivo.modules.oracleapi.service.BoomCostService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
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
    private static String URL_PLM_STAGE = "http://myivo.ivo.com.cn/im/restful/sampleInter.do";

    @Autowired
    private BmCostService bmCostService;

    @Autowired
    private BoomCostService bomService;

    /**
     * PLM接口-1
     * 根据机种名从PLM系统获取机种的阶段信息
     * @param project 机种
     * @return
     */
    @Override
    public List<Map<String, String>> getProjectStage(String project) {
        log.info("机种[" + project + "]访问PLM系统，PLM接口-1，url[" + URL_PLM_STAGE + "]...");
        List<Map<String, String>> list = restTemplate.getForObject(URL_PLM_STAGE + "?name=" + project, List.class);
        if (list == null) {
            list = new ArrayList<>();
        }
        log.info("访问PLM系统成功");
        return list;
    }

    // 获取项目成员
    @Override
    public List<Map<String, String>> getMembers() {
        List<Map<String, String>> memberList = new ArrayList<>();
        Map<String, String> map1 = new HashMap();
        map1.put("user", "C1504031");
        map1.put("userName", "张俊");
        map1.put("role", "PM");
        memberList.add(map1);
        Map<String, String> map2 = new HashMap();
        map2.put("user", "C0803616");
        map2.put("userName", "杨明华");
        map2.put("role", "PJM");
        memberList.add(map2);
        Map<String, String> map3 = new HashMap();
        map3.put("user", "C1504022");
        map3.put("userName", "柏宁");
        map3.put("role", "Sales");
        memberList.add(map3);
        Map<String, String> map4 = new HashMap();
        map4.put("user", "C0910009");
        map4.put("userName", "彭奎祥");
        map4.put("role", "FAE");
        memberList.add(map4);
        Map<String, String> map5 = new HashMap();
        map5.put("user", "C1607854");
        map5.put("userName", "张昭");
        map5.put("role", "FAE");
        memberList.add(map5);
        Map<String, String> map6 = new HashMap();
        map6.put("user", "C1505006");
        map6.put("userName", "姚福");
        map6.put("role", "FAE");
        memberList.add(map6);
        Map<String, String> map7 = new HashMap();
        map7.put("user", "C1407065");
        map7.put("userName", "吴佳星");
        map7.put("role", "LCD RD");
        memberList.add(map7);

        Map<String, String> map8 = new HashMap();
        map8.put("user", "C1607988");
        map8.put("userName", "权威");
        map8.put("role", "LCD RD");
        memberList.add(map8);

        Map<String, String> map9 = new HashMap();
        map9.put("user", "C1607932");
        map9.put("userName", "罗雨钟");
        map9.put("role", "ME RD");
        memberList.add(map9);

        Map<String, String> map10 = new HashMap();
        map10.put("user", "C1207981");
        map10.put("userName", "马录俊");
        map10.put("role", "EE RD");
        memberList.add(map10);

        Map<String, String> map11 = new HashMap();
        map11.put("user", "C1207006");
        map11.put("userName", "叶茂林");
        map11.put("role", "RD-Packing");
        memberList.add(map11);

        Map<String, String> map12 = new HashMap();
        map12.put("user", "C1405005");
        map12.put("userName", "孙雪娇");
        map12.put("role", "DQE");
        memberList.add(map12);


        Map<String, String> map13 = new HashMap();
        map13.put("user", "C1712004");
        map13.put("userName", "刘宇");
        map13.put("role", "NPE-Array");
        memberList.add(map13);

        Map<String, String> map14 = new HashMap();
        map14.put("user", "C1604004");
        map14.put("userName", "马寿舫");
        map14.put("role", "NPE-Cell");
        memberList.add(map14);

        Map<String, String> map15 = new HashMap();
        map15.put("user", "C1701002");
        map15.put("userName", "张增坤");
        map15.put("role", "LCM TEC");
        memberList.add(map15);
        return memberList;
    }

    @Override
    public List<Map<String, String>> getStageFromPlm(String project) {
        log.info("访问PLM获取机种阶段信息," + "url["+URL_PLM_STAGE+"]" + "project["+project+"]");
        Map<String, String> variables = new HashMap<>();
        variables.put("name", project);
        return restTemplate.getForObject(URL_PLM_STAGE+"?name=" + project, List.class, variables);
    }

    @Override
    public List<Map<String, String>> getScheduleFromPlm(String project) {
        return getSchedule();
    }

    @Override
    public List<Map<String, String>> getMembersFromPlm(String project) {
        return getMembers();
    }

    public List<Map<String, String>> getSchedule() {
        List<Map<String, String>> memberList = new ArrayList<>();
        Map<String, String> map1 = new HashMap();
        map1.put("stage", "Kick Off");
        map1.put("date", "2018-01-02");
        map1.put("status", "complete");
        memberList.add(map1);

        Map<String, String> map2 = new HashMap();
        map2.put("stage", "NPRB");
        map2.put("date", "2018-01-29");
        map2.put("status", "complete");
        memberList.add(map2);

        Map<String, String> map4 = new HashMap();
        map4.put("stage", "Design review");
        map4.put("date", "2018-03-01");
        map4.put("status", "complete");
        memberList.add(map4);

        Map<String, String> map5 = new HashMap();
        map5.put("stage", "EVT");
        map5.put("date", "2018-09-21");
        map5.put("status", "run");
        memberList.add(map5);

        Map<String, String> map6 = new HashMap();
        map6.put("stage", "DVT");
        map6.put("date", "2019-08-22");
        map6.put("status", "wait");
        memberList.add(map6);


        Map<String, String> map7 = new HashMap();
        map7.put("stage", "PVT");
        map7.put("date", "2019-09-18");
        map7.put("status", "wait");
        memberList.add(map7);

        Map<String, String> map8 = new HashMap();
        map8.put("stage", "MP");
        map8.put("date", "2019-09-25");
        map8.put("status", "wait");
        memberList.add(map8);
        return memberList;
    }


    @Override
    public List<Map<String, Object>> getToolCapitalFromBm(String project, Date fromDate, Date toDate) {
        return bmCostService.getToolCapital(fromDate, toDate, project);
    }

    @Override
    public List<Map<String, Object>> getToolOtherFromBm(String project, Date fromDate, Date toDate) {
        return bmCostService.getToolOther(fromDate, toDate, project);
    }

    @Override
    public List<Map<String, Object>> getDirectMaterialCost_thinFromBm(String project, Date fromDate, Date toDate) {
        return bmCostService.getDirectMaterial(fromDate, toDate, project);
    }

    @Override
    public List<Map<String, Object>> getValidationCostFromBm(String project, Date fromDate, Date toDate) {
        return bmCostService.getValidation(fromDate, toDate, project);
    }

    @Override
    public String getEeFromEd(String ed) {
        String ee;
        switch (ed) {
            case "ED180300010" : ee="EE180300129"; break;
            case "ED180300023" : ee="EE180300249"; break;
            case "ED180500002" : ee="EE180500015"; break;
            case "ED180500010" : ee="EE180500215"; break;
            case "ED180600019" : ee="EE180600139"; break;
            case "ED180700009" : ee="EE180600155"; break;
            case "ED190200010" : ee="EE190200188"; break;
            case "ED190600014" : ee="EE190600099"; break;
            case "ED190700005" : ee="EE190700309"; break;
            default: ee = null;
        }
        return ee;
    }

    @Override
    public String getProdIdFromEE(String ee) {
        String prodId;
        switch (ee) {
            case "EE180300129" : prodId = "AE14J4A01N"; break;
            case "EE180500015" : prodId = "AE14J4A01N"; break;
            case "EE180600139" : prodId = "AE14J4A02N"; break;
            case "EE190200188" : prodId = "AE14J4A02N"; break;
            default: prodId = null;
        }
        return prodId;
    }

    @Override
    public Map<String, String> getPfcdFromEE(String ee) {
        Map<String, String> map = new HashMap<>();
        switch (ee) {
            case "EE180300249":
                map.put("pfcd", "E140TDHAB0");
                map.put("tft", "E140TDHWB0Z");
                map.put("cf", "E140CDHWA0Z");
                break;
            case "EE180500215":
                map.put("pfcd", "E140TDHAB0");
                map.put("tft", "E140TDHWB0Z");
                map.put("cf", "E140CDHWA0Z");
                break;
            case "EE180600155":
                map.put("pfcd", "E140TDHAB1");
                map.put("tft", "E140TDHWB1Z");
                map.put("cf", "E140CDHWA1Z");
                break;
            case "EE190600099":
                map.put("pfcd", "E140TDHAB1");
                map.put("tft", "E140TDHWB1Z");
                map.put("cf", "E140CDHWA1Z");
                break;
            case "EE190700309":
                map.put("pfcd", "E140TDHAB1");
                map.put("tft", "E140TDHWB1Z");
                map.put("cf", "E140CDHWA1Z");
                break;
        }
        return map;
    }

    @Override
    public List<String> getWoFromEE(String ee) {
        List<String> list = new ArrayList<>();
        switch (ee) {
            case "EE180500053" :
                list.add("IL2M185117");
                list.add("IL2M185118");
                break;
            case "EE180800226" :
                list.add("IL2M188120");
                break;
            case "EE180700176" :
                list.add("IL2M187108");
                list.add("IL2M187109"); break;
            case "EE181200519" :
                list.add("IL2M191110");
                list.add("IL2M191111");
                list.add("IL2M191112");
                list.add("IL2M191113"); break;
        }
        return list;
    }

    @Override
    public Map<String, String> getMaterialAndVersion(String prodId) {
        return bomService.getMaterialAndVersion(prodId);
    }

    @Override
    public Double getMaterialPriceForArrayFromBom(String prodId) {
        return bomService.getPriceForArray(prodId);
    }

    @Override
    public Double getMaterialPriceForCellFromBom(String prodId) {
        return bomService.getPriceForCell(prodId);
    }

    @Override
    public Double getWoCostForModuleFromBom(String wo) {
        return bomService.getWoCost(wo);
    }

    @Override
    public List<Map<String, Object>> getQmsVerefication(String project) {
        return null;
    }
}
