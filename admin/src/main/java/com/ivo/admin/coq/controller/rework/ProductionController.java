package com.ivo.admin.coq.controller.rework;

import com.ivo.common.utils.ResultVoUtil;
import com.ivo.common.vo.ResultVo;
import com.ivo.modules.coq.domain.rework.ArrayProduction;
import com.ivo.modules.coq.domain.rework.CellProduction;
import com.ivo.modules.coq.domain.rework.ModuleProduction;
import com.ivo.modules.coq.domain.rework.Station;
import com.ivo.modules.coq.service.rework.ProductionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author wj
 * @version 1.0
 */
@Controller
@RequestMapping("cop/rework/production")
@Slf4j
public class ProductionController {

    @Autowired
    private ProductionService productionService;

    /**
     * 返回视图
     * @return
     */
    @GetMapping("/view")
    public String stationView() {
        return "/coq/rework/production";
    }

    @RequestMapping("/getArrayProduction")
    @ResponseBody
    public ResultVo getArrayProduction(String project) {
        List<ArrayProduction> arrayProductionList = productionService.getArrayProduction(project);
        return ResultVoUtil.success(arrayProductionList);
    }

    @RequestMapping("/getCellProduction")
    @ResponseBody
    public ResultVo getCellProduction(String project) {
        List<CellProduction> cellProductionList = productionService.getCellProduction(project);
        return ResultVoUtil.success(cellProductionList);
    }

    @RequestMapping("/getModuleProduction")
    @ResponseBody
    public ResultVo getModuleProduction(String project) {
        List<ModuleProduction> moduleProductionList = productionService.getModuleProduction(project);
        return ResultVoUtil.success(moduleProductionList);
    }
}
