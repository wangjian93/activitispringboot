package com.ivo.component.shiro.config;

import com.ivo.modules.system.domain.User;
import com.ivo.modules.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * 使用SpringData审计功能，审核员自动赋值配置
 * @Author: wj
 * @Date: 2019-07-03 16:30
 * @Version 1.0
 */
@Configuration
public class AuditorConfig implements AuditorAware<User> {

    @Autowired
    private UserService userService;

    @Override
    public Optional<User> getCurrentAuditor() {
//        Subject subject = SecurityUtils.getSubject();
//        User user = (User) subject.getPrincipal();
        User user = userService.getUserById("admin");
        return Optional.ofNullable(user);
    }
}
