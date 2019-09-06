package com.ivo.admin.system.controller;

import com.ivo.admin.system.validator.MenuValid;
import com.ivo.common.enums.ResultEnum;
import com.ivo.common.exception.ResultException;
import com.ivo.common.utils.ResultVoUtil;
import com.ivo.common.vo.ResultVo;
import com.ivo.modules.system.domain.Menu;
import com.ivo.modules.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
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
     * 跳转到页面
     */
    @GetMapping("/index")
    public String index() {
        return "/system/menu/menu";
    }

    /**
     * 菜单数据列表
     */
    @GetMapping("/getMenus")
    @ResponseBody
    public ResultVo list(Menu menu) {
        // 创建匹配器，进行动态查询匹配
        ExampleMatcher matcher = ExampleMatcher.matching().
                withMatcher("title", match -> match.contains())
                .withIgnorePaths("type");

        // 获取菜单列表
        Example<Menu> example = Example.of(menu, matcher);
        Sort sort = new Sort(Sort.Direction.ASC, "sort");

        List<Menu> list = menuService.getMenusByExample(example, sort);

        return ResultVoUtil.success(list);
    }

    /**
     * 添加菜单
     * @param menuValid
     * @param menu
     * @return
     */
    @RequestMapping("/saveMenu")
    @ResponseBody
    public ResultVo saveMenu(@Validated MenuValid menuValid, Menu menu) {
        // 设置menu的id
        List<Menu> menuList = menuService.getChildMenuByPid(menu.getPid());
        menu.setId(menu.getPid()*100  + (menuList.size()+1L));
        menu.setSort(menuList.size());
        menuService.saveMenu(menu);

        // 重新设置排序
        reSort(menu.getPid());

        return ResultVoUtil.success();
    }

    /**
     * 菜单重新排序
     */
    public void reSort(long  menuPid) {
        List<Menu> menuList = menuService.getChildMenuByPid(menuPid);
        for (int i = 0; i < menuList.size(); i++) {
            Menu menu = menuList.get(i);
            menu.setSort(i);
            menuService.saveMenu(menu);
        }
    }

    /**
     * 修改菜单
     * @param menuValid
     * @param menu
     * @return
     */
    @RequestMapping("/editMenu")
    @ResponseBody
    public ResultVo editMenu(@Validated MenuValid menuValid, Menu menu) {
        Menu v = menuService.getMenuById(menu.getId());
        if(v == null) {
            throw new ResultException(ResultEnum.MENU_NOTEXIST);
        }

        v.setPid(menu.getPid());
        v.setTitle(menu.getTitle());
        v.setUrl(menu.getUrl());
        v.setPerms(menu.getPerms());
        v.setIcon(menu.getIcon());
        v.setType(menu.getType());
        v.setSort(menu.getSort());
        menuService.saveMenu(v);

        reSort(menu.getPid());

        return ResultVoUtil.success();
    }

    /**
     * 删除菜单
     * @param menuId
     * @return
     */
    @RequestMapping("/deleteMenu")
    @ResponseBody
    public ResultVo deleteMenu(Long menuId) {
        Menu menu = menuService.getMenuById(menuId);
        if(menu == null) {
            throw new ResultException(ResultEnum.MENU_NOTEXIST);
        }

        List<Menu> menuList = menuService.getChildMenuByPid(menu.getId());
        menuList.forEach(m -> { menuService.deleteMenu(m); });

        menuService.deleteMenu(menu);

        reSort(menu.getPid());

        return ResultVoUtil.success();
    }

}
