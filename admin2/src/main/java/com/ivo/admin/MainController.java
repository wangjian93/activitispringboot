package com.ivo.admin;

import com.ivo.common.constant.AdminConst;
import com.ivo.component.shiro.ShiroUtil;
import com.ivo.modules.system.domain.Menu;
import com.ivo.modules.system.domain.Role;
import com.ivo.modules.system.domain.User;
import com.ivo.modules.system.enums.MenuTypeEnum;
import com.ivo.modules.system.service.MenuService;
import com.ivo.modules.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 系统服务
 * @Author: wj
 * @Date: 2019-05-29 16:13
 * @Version 1.0
 */
@Controller
public class MainController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    /**
     * 后台主体内容
     */
    @GetMapping("/")
    public String main(Model model){
        // 获取当前登录的用户
        User user = ShiroUtil.getSubject();

        // 菜单键值对(ID->菜单)
        Map<Long, Menu> keyMenu = new HashMap<>();

        // 管理员实时更新菜单
        // 其他用户需从相应的角色中获取菜单资源
        if(user.getId().equals(AdminConst.ADMIN_ID)){
            List<Menu> menus = menuService.getAllMenu();
            menus.forEach(menu -> keyMenu.put(menu.getId(), menu));
        }else {
            Set<Role> roleList = roleService.getUserOkRoleList(user.getId());
            roleList.forEach(role -> {
                role.getMenus().forEach(menu -> {
                    keyMenu.put(menu.getId(), menu);
                });
            });
        }

        // 封装菜单树形数据
        Map<Long, Menu> treeMenu = new HashMap<>();
        keyMenu.forEach((id, menu) -> {
            if(!menu.getType().equals(MenuTypeEnum.BUTTON.getCode())){
                if(keyMenu.get(menu.getPid()) != null){
                    keyMenu.get(menu.getPid()).getChildren().put(Long.valueOf(menu.getSort()), menu);
                }else{
                    treeMenu.put(Long.valueOf(menu.getSort()), menu);
                }
            }
        });

        model.addAttribute("user", user);
        model.addAttribute("treeMenu", treeMenu);
        return "/index";
    }

    /**
     * 主页
     */
    @GetMapping("/home")
    public String home() {
        return "/system/main/home";
    }

    /**
     * 主题设置弹窗页
     */
    @RequestMapping("/tpl/theme")
    public String theme() {
        return "/system/main/tpl-theme";
    }
}
