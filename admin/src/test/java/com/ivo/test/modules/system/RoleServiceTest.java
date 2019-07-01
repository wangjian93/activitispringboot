package com.ivo.test.modules.system;

import com.ivo.AbstractTest;
import com.ivo.modules.system.domain.Role;
import com.ivo.modules.system.service.RoleService;
import org.apache.shiro.util.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @Author: wj
 * @Date: 2019-06-06 14:41
 * @Version 1.0
 */
@Transactional
public class RoleServiceTest extends AbstractTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void test() {
        Set<Role> roles = roleService.getUserOkRoleList("a");
        Assert.notEmpty(roles);
    }
}
