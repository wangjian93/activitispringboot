package com.ivo.admin.coq.controller;

import com.ivo.modules.coq.cost.AmountFormatUtils;
import com.ivo.modules.coq.cost.DateUtil;
import com.ivo.modules.coq.domain.*;
import com.ivo.modules.coq.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wj
 * @Date: 2019-09-01 23:11
 * @Version 1.0
 */
@Controller
@RequestMapping("/cost")
@Slf4j
public class ProjectCostController {

    @Autowired
    private ProjectCostService2 projectCostService;

    @Autowired
    private PlmProjectStageService plmProjectStageService;

    @Autowired
    private PlmProjectMemberService memberService;

    @Autowired
    private EEProjectCostService eeProjectCostService;

    @Autowired
    private PlmProjectScheduleService scheduleService;

    @Autowired
    private BmProjectCostService bmProjectCostService;

    @Autowired
    private TravelProjectCostService travelProjectCostService;

    @GetMapping("/projectCost2")
    public String projectCostView(@RequestParam(defaultValue = "N1408 R0") String projectName, Model model) {
        ProjectCost2 projectCost = projectCostService.getProject(projectName);
        Map<String, List<PlmProjectStage>> stageMap = new HashMap<>();

        projectCost.getProjectStageCostList().forEach(projectStageCost -> {
            stageMap.put(projectStageCost.getStage(), plmProjectStageService.getPlmStageList(projectName, projectStageCost.getStage()));
        });

        List<PlmProjectMember> memberList = memberService.getPlmMemberList(projectName);

        List<EEProjectCost> eeList = eeProjectCostService.getEEProjectCost(projectName);

        List<PlmProjectSchedule> scheduleList = scheduleService.getPlmScheduleList(projectName);

        List<BmProjectCost> bmList = bmProjectCostService.getBmProjectCost(projectName);

        List<TravelProjectCost> travelList = travelProjectCostService.getTravelProjectCost(projectName);

        model.addAttribute("projectCost", projectCost);
        model.addAttribute("stageMap", stageMap);
        model.addAttribute("memberList", memberList);
        model.addAttribute("eeList", eeList);
        model.addAttribute("scheduleList", scheduleList);
        model.addAttribute("bmList", bmList);
        model.addAttribute("travelList", travelList);
        model.addAttribute("amountFormatUtils", new AmountFormatUtils());
        model.addAttribute("dateUtil", new DateUtil());
        return "/coq/projectCost2";
    }


}
