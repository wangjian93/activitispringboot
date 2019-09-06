package com.ivo.test.system;

import com.ivo.modules.system.domain.User;
import com.ivo.modules.system.service.UserService;
import com.ivo.test.AbstractTest;
import org.apache.shiro.util.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: wj
 * @Date: 2019-07-04 16:49
 * @Version 1.0
 */
public class UserServiceTest extends AbstractTest {

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        User user = userService.getUserById("admin");
        Assert.notNull(user);
    }
}
