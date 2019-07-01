package com.ivo.modules.system.repository;

import com.ivo.modules.system.domain.User;

/**
 * @Author: wj
 * @Date: 2019-06-04 16:11
 * @Version 1.0
 */
public interface UserRepository extends BaseRepository<User, String> {

    /**
     * 根据用户名查询用户数据
     * @param username 用户名
     * @return 用户数据
     */
    User findByUsername(String username);
}
