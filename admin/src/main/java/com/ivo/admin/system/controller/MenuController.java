package com.ivo.admin.system.controller;

import com.ivo.common.utils.HttpServletUtil;
import com.ivo.common.utils.ResultVoUtil;
import com.ivo.common.vo.ResultVo;
import com.ivo.component.thymleaf.utility.DictUtil;
import com.ivo.modules.system.domain.Menu;
import com.ivo.modules.system.service.MenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
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
    @RequiresPermissions("system:menu:index")
    public String index(Model model) {
        String search = HttpServletUtil.getRequest().getQueryString();
        model.addAttribute("search", search);
        return "/system/menu/index";
    }

    /**
     * 菜单数据列表
     */
    @GetMapping("/list")
    @RequiresPermissions("system:menu:index")
    @ResponseBody
    public ResultVo list(Menu menu) {
        // 创建匹配器，进行动态查询匹配
        ExampleMatcher matcher = ExampleMatcher.matching().
                withMatcher("title", match -> match.contains());

        // 获取菜单列表
        Example<Menu> example = Example.of(menu, matcher);
        Sort sort = new Sort(Sort.Direction.ASC, "sort");
        List<Menu> list = menuService.getListByExample(example, sort);

        // TODO: 2019/2/25 菜单类型处理方案
        list.forEach(editMenu -> {
            String type = String.valueOf(editMenu.getType());
            editMenu.setRemark(DictUtil.keyValue("MENU_TYPE", type));
        });
        return ResultVoUtil.success(list);
    }


}
