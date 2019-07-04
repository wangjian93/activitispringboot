package com.ivo.admin.system.controller;

import com.ivo.common.utils.ResultVoUtil;
import com.ivo.common.vo.ResultVo;
import com.ivo.component.thymleaf.utility.DictUtil;
import com.ivo.modules.system.domain.Menu;
import com.ivo.modules.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 菜单管理
 * @Author: wj
 * @Date: 2019-06-10 22:22
 * @Version 1.0
 */
@Controller
@RequestMapping("/system/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 跳转到列表页面
     */
    @GetMapping("/index")
    public String index() {
        return "/system/menu/index";
    }

    /**
     * 菜单数据列表
     */
    @GetMapping("/list")
    @ResponseBody
    public ResultVo list(Menu menu) {
        // 创建匹配器，进行动态查询匹配
        ExampleMatcher matcher = ExampleMatcher.matching().
                withMatcher("title", match -> match.contains());

        // 获取菜单列表
        Example<Menu> example = Example.of(menu, matcher);
        Sort sort = new Sort(Sort.Direction.ASC, "sort");

        List<Menu> list = menuService.getMenusByExample(example, sort);

        list.forEach(editMenu -> {
            String type = String.valueOf(editMenu.getType());
            editMenu.setRemark(DictUtil.keyValue("MENU_TYPE", type));
        });
        return ResultVoUtil.success(list);
    }


}
