package com.ivo.modules.system.service.impl;

import com.ivo.common.enums.StatusEnum;
import com.ivo.modules.system.domain.Role;
import com.ivo.modules.system.domain.RoleUserRelationship;
import com.ivo.modules.system.repository.RoleRepository;
import com.ivo.modules.system.repository.RoleUserRelationshipRepository;
import com.ivo.modules.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: wj
 * @Date: 2019-06-04 17:14
 * @Version 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleUserRelationshipRepository relationshipRepository;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * 根据ID获取角色
     * @param id
     * @return
     */
    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    /**
     * 获取所有角色
     * @return
     */
    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    /**
     * 分页获取角色
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Role> getPageRoles(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return roleRepository.findAll(pageable);
    }

    /**
     * 保存Role
     * @param role
     * @return
     */
    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    /**
     * 删除Role
     * @param role
     */
    @Override
    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }

    /**
     * 获取用户的角色列表
     * @param userid 用户ID
     */
    @Override
    public Set<Role> getUserOkRoleList(String userid) {
        List<RoleUserRelationship> roleUserList = relationshipRepository.findByUser_userid(userid);
        Set<Role> roleList = new HashSet<>();
        for(RoleUserRelationship roleUser : roleUserList) {
            Role role = roleUser.getRole();
            if(role == null) {
                continue;
            }
            roleList.add(role);
        }
        return roleList;
    }

    @Override
    public Boolean existsUserOk(String userid) {
        return null;
    }

    /**
     * 根据角色ID查询角色数据
     * @param id 角色ID
     */
    @Override
    public Role getById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Role> getPageList(Example<Role> example) {
        return null;
    }

    @Override
    public List<Role> getListBySortOk(Sort sort) {
        return null;
    }

    @Override
    public boolean repeatByName(Role role) {
        return false;
    }

    @Override
    public Role save(Role role) {
        return null;
    }

    @Override
    public Boolean updateStatus(StatusEnum statusEnum, List<Long> idList) {
        return null;
    }
}
