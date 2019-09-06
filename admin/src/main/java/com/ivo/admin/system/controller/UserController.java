package com.ivo.admin.system.controller;

import com.ivo.admin.system.validator.UserValid;
import com.ivo.common.enums.ResultEnum;
import com.ivo.common.exception.ResultException;
import com.ivo.common.utils.HttpServletUtil;
import com.ivo.common.utils.ResultVoUtil;
import com.ivo.common.vo.ResultVo;
import com.ivo.component.shiro.ShiroUtil;
import com.ivo.modules.system.domain.Role;
import com.ivo.modules.system.domain.RoleUserRelationship;
import com.ivo.modules.system.domain.User;
import com.ivo.modules.system.service.RoleService;
import com.ivo.modules.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 用户管理
 * @Author: wj
 * @Date: 2019-07-09 12:57
 * @Version 1.0
 */
@Controller
@RequestMapping("/system/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 跳转到页面
     * @return
     */
    @GetMapping("/index")
    public String index() {
        return "/system/user/user";
    }

    /**
     * 分页查询用户
     * @param page
     * @param limit
     * @param user
     * @return
     */
    @RequestMapping("/getUsers")
    @ResponseBody
    public ResultVo getUsers(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit,
                             @RequestParam(defaultValue = "userid") String field, @RequestParam(defaultValue = "asc") String order,
                             User user) {

        // 创建匹配器，进行动态查询匹配
        ExampleMatcher matcher = ExampleMatcher.matching().
                withMatcher("userid", match -> match.contains())
                .withMatcher("username", match -> match.contains())
                .withIgnorePaths("type", "status");

        // 获取菜单列表
        Example<User> example = Example.of(user, matcher);
        Sort sort = new Sort(Sort.Direction.fromString(order), field);

        Page<User> pageUsers = userService.getPageUsers(page-1, limit, example, sort);
        List<User> userList = pageUsers.getContent();
        ResultVo resultVo = ResultVoUtil.success(userList);
        resultVo.setCount(pageUsers.getTotalElements());
        return resultVo;
    }

    /**
     * 添加用户
     * @param userValid
     * @param user
     * @return
     */
    @PostMapping("/saveUser")
    @ResponseBody
    public ResultVo saveUser(@Validated UserValid userValid, User user) {

        // 判断两次密码是否一致
        if (!user.getPassword().equals(userValid.getPasswordConfirm())) {
            throw new ResultException(ResultEnum.USER_INEQUALITY);
        }

        // 判断用户名是否重复
        if (userService.getUserById(user.getUserid()) != null) {
            throw new ResultException(ResultEnum.USER_EXIST);
        }

        // 对密码进行加密
        String salt = ShiroUtil.getRandomSalt();
        String encrypt = ShiroUtil.encrypt(user.getPassword(), salt);
        user.setPassword(encrypt);
        user.setSalt(salt);

        userService.saveUser(user);
        return ResultVoUtil.success();
    }

    /**
     * 修改用户
     * @param userValid
     * @param user
     * @return
     */
    @PostMapping("/editUser")
    @ResponseBody
    public ResultVo editUser(@Validated UserValid userValid, User user) {
        user.getUserid();
        return ResultVoUtil.success();
    }

    /**
     * 删除用户
     * @param userid
     * @return
     */
    @PostMapping("/deleteUser")
    @ResponseBody
    public ResultVo deleteUser(String userid) {
        User user = userService.getUserById(userid);
        if(user == null) {
            throw new ResultException(ResultEnum.USER_NOTEXIST);
        }
        userService.deleteUser(user);
        return ResultVoUtil.success();
    }

    @RequestMapping("/getRolesForUser")
    @ResponseBody
    public ResultVo getRolesForUser(String userid) {
        User user = userService.getUserById(userid);
        if(user == null) {
            throw new ResultException(ResultEnum.USER_NOTEXIST);
        }

        Set<Role> userRoles = userService.getRolesByUser(user);
        Set<Long> roleIds = new HashSet<>();
        userRoles.forEach(role -> {
            roleIds.add(role.getId());
        });

        List<Role> roleList = roleService.getAllRoles();

        List<Map> options = new ArrayList<>();
        roleList.forEach(role -> {
            Map<String, Object> optionMap = new HashMap<>();
            optionMap.put("id", role.getId());
            optionMap.put("name", role.getName());
            optionMap.put("title", role.getTitle());
            optionMap.put("checked", roleIds.contains(role.getId())? true : false);
            options.add(optionMap);
        });

        return ResultVoUtil.success(options);
    }


    @RequestMapping("/assignRole")
    @ResponseBody
    public ResultVo assignRole(String userid, @RequestParam(value = "roleIds[]", required = false) long[] roleIds) {
        User user = userService.getUserById(userid);
        if(user == null) {
            throw new ResultException(ResultEnum.USER_NOTEXIST);
        }

        List<Role> roleList = new ArrayList<>();
        if(roleIds != null) {
            for(long roleId : roleIds) {
                Role role = roleService.getRoleById(roleId);
                if(user == null) {
                    throw new ResultException(ResultEnum.ROLE_NOTEXIST);
                }
                roleList.add(role);
            }
        }

        userService.saveRoleUserRelationship(user, roleList);

        return ResultVoUtil.success();
    }


}
