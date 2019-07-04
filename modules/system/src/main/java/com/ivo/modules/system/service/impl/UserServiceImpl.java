package com.ivo.modules.system.service.impl;

import com.ivo.modules.system.domain.User;
import com.ivo.modules.system.repository.UserRepository;
import com.ivo.modules.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * @param id
     * @return
     */
    @Override
    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * 获取所有用户
     * @return
     */
    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
