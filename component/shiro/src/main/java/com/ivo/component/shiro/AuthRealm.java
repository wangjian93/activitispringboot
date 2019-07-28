package com.ivo.component.shiro;

import com.ivo.common.constant.AdminConst;
import com.ivo.modules.system.domain.Role;
import com.ivo.modules.system.domain.User;
import com.ivo.modules.system.service.RoleService;
import com.ivo.modules.system.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * 用户认证以及授权
 * @Author: wj
 * @Date: 2019-05-29 14:16
 * @Version 1.0
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 授权逻辑
     * @param principal
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 获取用户Principal对象
        User user = (User) principal.getPrimaryPrincipal();

        // 如果是超级管理员的内置账号，拥有所有权限
        if(user.getUserid().equals(AdminConst.ADMIN_ID)){
            info.addRole(AdminConst.ADMIN_ROLE_NAME);
            info.addStringPermission(AdminConst.PERMISSION);
            return info;

        } else {
            // 获取角色和资源
            Set<Role> roleList = roleService.getUserOkRoleList(user.getUserid());
            // 赋予角色和资源授权
            roleList.forEach(role -> {
                info.addRole(role.getName());
                role.getMenus().forEach(menu -> {
                    String perms = menu.getPerms();
                    if(!StringUtils.isEmpty(perms) && !perms.contains("*")) {
                        info.addStringPermission(perms);
                    }
                });
            });
            return info;
        }
    }

    /**
     * 认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        User user = null;
        // 如果是超级管理员使用内置账号
        if(StringUtils.equals(token.getUsername(), AdminConst.ADMIN_ID)) {
            user = new User();
            user.setUserid(AdminConst.ADMIN_ID);
            user.setUsername(AdminConst.ADMIN_NAME);
            user.setPassword(AdminConst.PASSWORD);
            user.setSalt(AdminConst.SALT);
        } else {
            // 获取数据库中的用户名密码
            user = userService.getUserById(token.getUsername());
            // 判断用户名是否存在
            if(user == null){
                throw new UnknownAccountException();
            }
        }

        // 对盐进行加密处理
        ByteSource salt = ByteSource.Util.bytes(user.getSalt());

        /* 传入密码自动判断是否正确
         * 参数1：传入对象给Principal
         * 参数2：正确的用户密码
         * 参数3：加盐处理
         * 参数4：固定写法
         */
        return new SimpleAuthenticationInfo(user, user.getPassword(), salt, getName());
    }

    /**
     * 自定义密码验证匹配器
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        setCredentialsMatcher(new SimpleCredentialsMatcher() {
            @Override
            public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
                UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
                SimpleAuthenticationInfo info = (SimpleAuthenticationInfo) authenticationInfo;
                // 获取明文密码及密码盐
                String password = String.valueOf(token.getPassword());
                String salt = CodecSupport.toString(info.getCredentialsSalt().getBytes());

                return equals(ShiroUtil.encrypt(password, salt), info.getCredentials());
            }
        });
    }
}
