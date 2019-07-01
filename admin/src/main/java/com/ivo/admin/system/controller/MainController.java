package com.ivo.admin.system.controller;

import com.ivo.common.constant.AdminConst;
import com.ivo.component.shiro.ShiroUtil;
import com.ivo.modules.system.domain.Menu;
import com.ivo.modules.system.domain.Role;
import com.ivo.modules.system.domain.User;
import com.ivo.modules.system.enums.MenuTypeEnum;
import com.ivo.modules.system.service.MenuService;
import com.ivo.modules.system.service.RoleService;
import com.ivo.modules.system.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    /**
     * 后台主体内容
     */
    @GetMapping("/")
    @RequiresPermissions("index")
    public String main(Model model){
        // 获取当前登录的用户
        User user = ShiroUtil.getSubject();

        // 菜单键值对(ID->菜单)
        Map<Long, Menu> keyMenu = new HashMap<>();

        // 管理员实时更新菜单
        if(user.getId().equals(AdminConst.ADMIN_ID)){
            List<Menu> menus = menuService.getListBySortOk();
            menus.forEach(menu -> keyMenu.put(menu.getId(), menu));
        }else{
            Set<Role> roleList = roleService.getUserOkRoleList(user.getId());
            // 其他用户需从相应的角色中获取菜单资源
            roleList.forEach(role -> {
                role.getMenus().forEach(menu -> {
                    keyMenu.put(menu.getId(), menu);
                });
            });
        }

        // 封装菜单树形数据
        Map<Long, Menu> treeMenu = new HashMap<>();
        keyMenu.forEach((id, menu) -> {
            if(!menu.getType().equals(MenuTypeEnum.NOT_MENU.getCode())){
                if(keyMenu.get(menu.getPid()) != null){
                    keyMenu.get(menu.getPid()).getChildren().put(Long.valueOf(menu.getSort()), menu);
                }else{
                    if(menu.getType().equals(MenuTypeEnum.TOP_LEVEL.getCode())){
                        treeMenu.put(Long.valueOf(menu.getSort()), menu);
                    }
                }
            }
        });

        model.addAttribute("user", user);
        model.addAttribute("treeMenu", treeMenu);
        return "/main";
    }

    /**
     * 主页
     */
    @GetMapping("/index")
    @RequiresPermissions("index")
    public String index(Model model){
        return "/system/main/index";
    }
}
