package com.ivo.admin.coq.controller;

import com.ivo.modules.coq.cost.DoubleUtil;
import com.ivo.modules.coq.domain.ProjectCost;
import com.ivo.modules.coq.domain.ProjectCost3;
import com.ivo.modules.coq.service.ProjectCostService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 新产品费用总表
 * @Author: wj
 * @Date: 2019-06-25 15:24
 * @Version 1.0
 */
@Controller
@Slf4j
@RequestMapping("/cost")
public class TotalCost {

    @Autowired
    private ProjectCostService service;

    @GetMapping("/projectCost")
    public String projectCostView(@RequestParam(defaultValue = "S0171 R0") String projectName, Model model) {
        ProjectCost projectCost = service.getProjectCost(projectName);

        //二级科目
        List<Map> secondSubject = new ArrayList<>();
        Map<String, Object> Design = new HashMap<>();
        Design.put("stage", "Design");
        Map<String, Object> NPR = new HashMap<>();
        NPR.put("stage", "NPR");
        Map<String, Object> DVT = new HashMap<>();
        DVT.put("stage", "DVT");
        Map<String, Object> EVT = new HashMap<>();
        EVT.put("stage", "EVT");
        Map<String, Object> PVT = new HashMap<>();
        PVT.put("stage", "PVT");
        secondSubject.add(Design);
        secondSubject.add(NPR);
        secondSubject.add(DVT);
        secondSubject.add(EVT);
        secondSubject.add(PVT);
        secondSubject.forEach(map -> {
            map.put("precautionCost", null);
            map.put("identifyCost", null);
            map.put("inLossCost", null);
            map.put("outLossCost", null);
        });

        projectCost.getProjectStageCosts().forEach(projectStageCost -> {
            // 阶段截取掉"-"及后内容
            String stage = StringUtils.substringBefore(projectStageCost.getStage(), "-");
            Map<String, Object> map = new HashMap();
            if(stage.equals("Design")) {
                map = Design;
            } else if (stage.equals("NPR")) {
                map = NPR;
            } else if (stage.equals("DVT")) {
                map = DVT;
            } else if (stage.equals("EVT")) {
                map = EVT;
            } else if (stage.equals("PVT")) {
                map = PVT;
            }

            map.put("precautionCost", DoubleUtil.sum((Double) map.get("precautionCost"), projectStageCost.getPrecautionCost()));
            map.put("identifyCost", DoubleUtil.sum((Double) map.get("identifyCost"), projectStageCost.getIdentifyCost()));
            map.put("inLossCost", DoubleUtil.sum((Double) map.get("inLossCost"), projectStageCost.getInLossCost()));
            map.put("outLossCost", DoubleUtil.sum((Double) map.get("outLossCost"), projectStageCost.getOutLossCost()));
        });

        model.addAttribute("projectCost", projectCost);
        model.addAttribute("secondSubject", secondSubject);
        return "/coq/projectCost";
    }

    @RequestMapping("/getProjectCost")
    @ResponseBody
    public ProjectCost getProjectCost(String projectName) {
        return service.getProjectCost(projectName);
    }

    @GetMapping("/totalCost")
    public String totalCost(Model model) {
        List<ProjectCost> list = new ArrayList<>();
        ProjectCost projectCost1 = service.getProjectCost("N1407");
        ProjectCost projectCost2 = service.getProjectCost("N1406");
        ProjectCost projectCost3 = service.getProjectCost("N1405");
        list.add(projectCost1);
        list.add(projectCost2);
        list.add(projectCost3);
        model.addAttribute("projectCosts", list);
        return "/coq/totalCost";
    }
}
