package com.ivo.modules.system.service;

import com.ivo.modules.system.domain.Role;
import com.ivo.modules.system.domain.RoleUserRelationship;
import com.ivo.modules.system.domain.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Set;

/**
 * 用户service
 * @Author: wj
 * @Date: 2019-06-04 16:16
 * @Version 1.0
 */
public interface UserService {

    /**
     * 根据ID获取用户
     * @param userid
     * @return
     */
    User getUserById(String userid);

    /**
     * 获取所有用户
     * @return
     */
    List<User> getAllUsers();

    /**
     * 根据查询实例分页查询用户
     * @param page 当前页数
     * @param size 每页条数
     * @param example 查询实例
     * @param sort 排序
     * @return
     */
    Page<User> getPageUsers(int page, int size, Example<User> example, Sort sort);

    /**
     * 保存用户
     * @param user 用户实体类
     */
    User saveUser(User user);

    /**
     * 用户删除
     * @param user
     */
    void deleteUser(User user);

    /**
     * 获取用户分配的角色
     * @param user
     * @return
     */
    Set<Role> getRolesByUser(User user);

    /**
     * 用户保存分配的角色
     * @param user
     * @param roleList
     * @return
     */
    RoleUserRelationship saveRoleUserRelationship(User user, List<Role> roleList);
}
