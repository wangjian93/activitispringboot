package com.ivo.admin.system.controller;

import com.ivo.admin.system.validator.RoleValid;
import com.ivo.common.enums.ResultEnum;
import com.ivo.common.exception.ResultException;
import com.ivo.common.utils.ResultVoUtil;
import com.ivo.common.vo.ResultVo;
import com.ivo.modules.system.domain.Menu;
import com.ivo.modules.system.domain.Role;
import com.ivo.modules.system.service.MenuService;
import com.ivo.modules.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 角色管理
 * @Author: wj
 * @Date: 2019-07-10 08:23
 * @Version 1.0
 */
@Controller
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /**
     * 跳转到页面
     * @return
     */
    @GetMapping("/index")
    public String index() {
        return "/system/role/role";
    }

    /**
     * 分页查询角色
     */
    @RequestMapping("/getRoles")
    @ResponseBody
    public ResultVo getUsers(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        Page pageRoles = roleService.getPageRoles(page-1, size);
        ResultVo resultVo = ResultVoUtil.success(pageRoles.getContent());
        resultVo.setCount(pageRoles.getTotalElements());
        return resultVo;
    }

    /**
     * 添加角色
     * @param roleValid
     * @param role
     * @return
     */
    @PostMapping("/saveRole")
    @ResponseBody
    public ResultVo saveRole(@Validated RoleValid roleValid, Role role) {
        roleService.saveRole(role);
        return ResultVoUtil.success();
    }

    /**
     * 删除用户
     * @param roleId
     * @return
     */
    @PostMapping("/deleteUser")
    @ResponseBody
    public ResultVo deleteUser(Long roleId) {
        Role role = roleService.getRoleById(roleId);
        if(role == null) {
            throw new ResultException(ResultEnum.USER_NOTEXIST);
        }
        roleService.deleteRole(role);
        return ResultVoUtil.success();
    }

    /**
     * 获取角色权限分配的菜单树
     * @param roleId
     * @return
     */
    @PostMapping("/getMenuTreeForRole")
    @ResponseBody
    public ResultVo getMenuTreeForRole(long roleId) {
        Role role = roleService.getRoleById(roleId);
        if(role == null) {
            throw new ResultException(ResultEnum.USER_NOTEXIST);
        }
        Set<Menu> menuSet = role.getMenus();
        Set<Long> menuIds = new HashSet<>();
        menuSet.forEach(menu -> {
            menuIds.add(menu.getId());
        });

        List<Menu> menuList = menuService.getAllMenu();

        List<Map> treeList = new ArrayList();

        menuList.forEach(menu -> {
            Map<String, Object> treeOptionMap = new HashMap<>();
            treeOptionMap.put("id", menu.getId());
            treeOptionMap.put("pId", menu.getPid());
            treeOptionMap.put("icon", menu.getIcon());
            treeOptionMap.put("name", menu.getTitle());
            treeOptionMap.put("open", true);
            treeOptionMap.put("checked", menuIds.contains(menu.getId()) ? true : false);

            treeList.add(treeOptionMap);
        });

        return ResultVoUtil.success(treeList);
    }


    /**
     * 给角色分配菜单权限
     * @param roleId
     * @param menuIds
     * @return
     */
    @RequestMapping("/assignPerm")
    @ResponseBody
    public ResultVo assignPerm(long roleId, @RequestParam(value = "menuIds[]", required = false) long[] menuIds) {
        Role role = roleService.getRoleById(roleId);
        if(role == null) {
            throw new ResultException(ResultEnum.USER_NOTEXIST);
        }

        Set<Menu> menuSet = new HashSet<>();
        if(menuIds != null) {
            for(long menuId : menuIds) {
                Menu menu = menuService.getMenuById(menuId);
                if(menu == null) {
                    throw new ResultException(ResultEnum.ROLE_NOTEXIST);
                }
                menuSet.add(menu);
            }
        }

        role.setMenus(menuSet);
        roleService.saveRole(role);
        return ResultVoUtil.success();
    }
}
