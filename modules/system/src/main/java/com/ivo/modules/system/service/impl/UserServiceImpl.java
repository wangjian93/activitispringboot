package com.ivo.modules.system.service.impl;

import com.ivo.modules.system.domain.Role;
import com.ivo.modules.system.domain.RoleUserRelationship;
import com.ivo.modules.system.domain.User;
import com.ivo.modules.system.repository.RoleUserRelationshipRepository;
import com.ivo.modules.system.repository.UserRepository;
import com.ivo.modules.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: wj
 * @Date: 2019-06-04 17:05
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleUserRelationshipRepository relationshipRepository;

    /**
     * 根据ID获取用户
     * @param userid
     * @return
     */
    @Override
    public User getUserById(String userid) {
        return userRepository.findById(userid).orElse(null);
    }

    /**
     * 获取所有用户
     * @return
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * 根据查询实例分页查询用户
     * @param page 当前页数
     * @param size 每页条数
     * @param example 查询实例
     * @param sort 排序
     * @return
     */
    @Override
    public Page<User> getPageUsers(int page, int size, Example example, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);
        return userRepository.findAll(example, pageable);
    }

    /**
     * 保存用户
     * @param user 用户实体类
     * @return
     */
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * 删除用户
     * @param user
     */
    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    /**
     * 获取用户分配的角色
     * @param user
     * @return
     */
    @Override
    public Set<Role> getRolesByUser(User user) {
        List<RoleUserRelationship> roleUserRelationshipList = relationshipRepository.findByUser_userid(user.getUserid());
        Set<Role> roleSet = new HashSet<>();
        roleUserRelationshipList.forEach(roleUserRelationship -> {
            roleSet.add(roleUserRelationship.getRole());
        });
        return roleSet;
    }

    /**
     * 用户保存角色
     * @param user
     * @param roleList
     * @return
     */
    @Override
    public RoleUserRelationship saveRoleUserRelationship(User user, List<Role> roleList) {
        // 去除已存在的角色
        List<RoleUserRelationship> roleUserRelationshipList = relationshipRepository.findByUser_userid(user.getUserid());
        roleUserRelationshipList.forEach(roleUserRelationship -> {
            relationshipRepository.delete(roleUserRelationship);
        });


        roleList.forEach(role -> {
            RoleUserRelationship roleUserRelationship = new RoleUserRelationship();
            roleUserRelationship.setUser(user);
            roleUserRelationship.setRole(role);
            relationshipRepository.save(roleUserRelationship);
        });

        return null;
    }
}
