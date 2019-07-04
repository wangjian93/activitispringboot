package com.ivo.modules.system.service;

import com.ivo.modules.system.domain.User;

import java.util.List;

/**
 * 用户service
 * @Author: wj
 * @Date: 2019-06-04 16:16
 * @Version 1.0
 */
public interface UserService {

    /**
     * 根据用户ID获取用户数据
     * @param id
     * @return
     */
    User getUserById(String id);

    /**
     * 获取所有用户
     * @return
     */
    List<User> getAllUser();
}
