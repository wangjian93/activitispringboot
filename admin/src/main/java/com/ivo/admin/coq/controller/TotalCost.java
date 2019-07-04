package com.ivo.admin.coq.controller;

import com.ivo.modules.coq.domain.ProjectCost;
import com.ivo.modules.coq.service.ProjectCostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

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
    public String projectCostView(@RequestParam(defaultValue = "") String projectName, Model model) {
        ProjectCost projectCost = service.getProjectCost(projectName);
        model.addAttribute("projectCost", projectCost);
        return "/coq/projectCost";
    }

    @RequestMapping("/getProjectCost")
    @ResponseBody
    public ProjectCost getProjectCost(String projectName) {
        return service.getProjectCost(projectName);
    }

}
