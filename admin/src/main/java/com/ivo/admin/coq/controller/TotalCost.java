package com.ivo.admin.coq.controller;

import com.ivo.modules.coq.cost.DoubleUtil;
import com.ivo.modules.coq.domain.BmProjectCost;
import com.ivo.modules.coq.domain.MaterialPrice;
import com.ivo.modules.coq.domain.PlmProjectSchedule;
import com.ivo.modules.coq.domain.ProjectCost;
import com.ivo.modules.coq.enums.StageTypeEnum;
import com.ivo.modules.coq.rest.RestService;
import com.ivo.modules.coq.service.BmProjectCostService;
import com.ivo.modules.coq.service.MaterialPriceService;
import com.ivo.modules.coq.service.PlmProjectScheduleService;
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

    @Autowired
    private MaterialPriceService materialPriceService;

    @Autowired
    private RestService restService;

    @Autowired
    private PlmProjectScheduleService scheduleService;

    @Autowired
    private BmProjectCostService bmProjectCostService;

    @GetMapping("/projectCost")
    public String projectCostView(@RequestParam(defaultValue = "N1408 R0") String projectName, Model model) {
        ProjectCost projectCost = service.getProjectCost(projectName);

        //二级科目
        List<Map> secondSubject = new ArrayList<>();
        Map<String, Object> Design = new HashMap<>();
        Design.put("stage", StageTypeEnum.DESIGN.getStage());
        Map<String, Object> NPR = new HashMap<>();
        NPR.put("stage", StageTypeEnum.NPRB.getStage());
        Map<String, Object> DVT = new HashMap<>();
        DVT.put("stage", StageTypeEnum.DVT.getStage());
        Map<String, Object> EVT = new HashMap<>();
        EVT.put("stage", StageTypeEnum.EVT.getStage());
        Map<String, Object> PVT = new HashMap<>();
        PVT.put("stage", StageTypeEnum.PVT.getStage());
        secondSubject.add(NPR);
        secondSubject.add(Design);
        secondSubject.add(EVT);
        secondSubject.add(DVT);
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

            if(StringUtils.containsAny(projectStageCost.getStage(), "EVT-1", "DVT-1")) {
                map.put("precautionCost", DoubleUtil.sum((Double) map.get("precautionCost"), projectStageCost.getPrecautionCost()));
            }

            map.put("identifyCost", DoubleUtil.sum((Double) map.get("identifyCost"), projectStageCost.getIdentifyCost()));
            map.put("inLossCost", DoubleUtil.sum((Double) map.get("inLossCost"), projectStageCost.getInLossCost()));
            map.put("outLossCost", DoubleUtil.sum((Double) map.get("outLossCost"), projectStageCost.getOutLossCost()));
        });


        // ED、EE关联的材料成本价格
        List<MaterialPrice> materialPriceList = new ArrayList<>();
        projectCost.getProjectStageCosts().forEach(projectStageCost -> {
            projectStageCost.getStageDetails().forEach(stageDetail -> {
                MaterialPrice materialPrice = materialPriceService.getMaterialPrice(stageDetail.getOrderNumber());
                if(materialPrice != null) {
                    materialPriceList.add(materialPrice);
                }
            });
        });

        List<Map<String, String>> memberList = restService.getMembers();

        model.addAttribute("memberList", memberList);
        model.addAttribute("projectCost", projectCost);
        model.addAttribute("secondSubject", secondSubject);
        model.addAttribute("materialPriceList", materialPriceList);

        List<PlmProjectSchedule> plmProjectScheduleList = scheduleService.getPlmScheduleList(projectName);
        List<BmProjectCost> bmTList = bmProjectCostService.getDetailForToolCost(projectName);
        List<BmProjectCost> bmVList = bmProjectCostService.getDetailForValidationCost(projectName);
        List<BmProjectCost> bmDList = bmProjectCostService.getDetailForDirectMaterialCost_thin(projectName);

        model.addAttribute("scheduleList", plmProjectScheduleList);
        model.addAttribute("bmTList", bmTList);
        model.addAttribute("bmVList", bmVList);
        model.addAttribute("bmDList", bmDList);
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

    @GetMapping("/vipByCostView")
    public String vipByCostView(@RequestParam(defaultValue = "N1408 R0") String projectName, Model model) {
        ProjectCost projectCost = service.getProjectCost(projectName);

        //二级科目
        List<Map> secondSubject = new ArrayList<>();
        Map<String, Object> Design = new HashMap<>();

        Design.put("stage", StageTypeEnum.DESIGN.getStage());
        Map<String, Object> NPR = new HashMap<>();
        NPR.put("stage", StageTypeEnum.NPRB.getStage());
        Map<String, Object> DVT = new HashMap<>();
        DVT.put("stage", StageTypeEnum.DVT.getStage());
        Map<String, Object> EVT = new HashMap<>();
        EVT.put("stage", StageTypeEnum.EVT.getStage());
        Map<String, Object> PVT = new HashMap<>();
        PVT.put("stage", StageTypeEnum.PVT.getStage());
        secondSubject.add(NPR);
        secondSubject.add(Design);
        secondSubject.add(EVT);
        secondSubject.add(DVT);
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

            if(StringUtils.containsAny(projectStageCost.getStage(), "EVT-1", "DVT-1")) {
                map.put("precautionCost", DoubleUtil.sum((Double) map.get("precautionCost"), projectStageCost.getPrecautionCost()));
            }

            map.put("identifyCost", DoubleUtil.sum((Double) map.get("identifyCost"), projectStageCost.getIdentifyCost()));
            map.put("inLossCost", DoubleUtil.sum((Double) map.get("inLossCost"), projectStageCost.getInLossCost()));
            map.put("outLossCost", DoubleUtil.sum((Double) map.get("outLossCost"), projectStageCost.getOutLossCost()));
        });


        // ED、EE关联的材料成本价格
        List<MaterialPrice> materialPriceList = new ArrayList<>();
        projectCost.getProjectStageCosts().forEach(projectStageCost -> {
            projectStageCost.getStageDetails().forEach(stageDetail -> {
                MaterialPrice materialPrice = materialPriceService.getMaterialPrice(stageDetail.getOrderNumber());
                if(materialPrice != null) {
                    materialPriceList.add(materialPrice);
                }
            });
        });

        model.addAttribute("projectCost", projectCost);
        model.addAttribute("secondSubject", secondSubject);

        Map<String, Map<String, Double>> map = new HashMap<>();
        map.put("precautionCost", newMap());
        map.put("identifyCost", newMap());
        map.put("inLossCost", newMap());
        map.put("outLossCost", newMap());

        secondSubject.forEach(m -> {
            map.get("precautionCost").put((String)m.get("stage"), (Double) m.get("precautionCost"));
            map.get("identifyCost").put((String)m.get("stage"), (Double) m.get("identifyCost"));
            map.get("inLossCost").put((String)m.get("stage"), (Double) m.get("inLossCost"));
            map.get("outLossCost").put((String)m.get("stage"), (Double) m.get("outLossCost"));
        });

        model.addAttribute("secondSubject2", map);

        return "/coq/vipByCost";
    }

    public Map<String, Double> newMap() {
        Map<String, Double> map = new HashMap<>();
        map.put(StageTypeEnum.NPRB.getStage(), null);
        map.put(StageTypeEnum.DESIGN.getStage(), null);
        map.put("EVT", null);
        map.put("DVT", null);
        map.put("PVT", null);
        map.put("MP", null);
        return map;
    }
}
