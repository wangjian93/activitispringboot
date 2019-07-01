package com.ivo.modules.system.service;

import com.ivo.modules.system.domain.User;

/**
 * @Author: wj
 * @Date: 2019-06-04 16:16
 * @Version 1.0
 */
public interface UserService {

    /**
     * 根据用户ID查询用户数据
     * @param id 用户ID
     */
    User getById(String id);

    /**
     * 根据用户名查询用户数据
     * @param username 用户名
     * @return 用户数据
     */
    User getByName(String username);
}
