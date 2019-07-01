package com.ivo.modules.system.service.impl;

import com.ivo.modules.system.domain.User;
import com.ivo.modules.system.repository.UserRepository;
import com.ivo.modules.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wj
 * @Date: 2019-06-04 17:05
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 根据用户ID获取用户信息
     * @param id 用户ID
     */
    @Override
    public User getById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * 根据用户名查询用户数据
     * @param username 用户名
     * @return 用户数据
     */
    @Override
    public User getByName(String username) {
        return userRepository.findByUsername(username);
    }
}
