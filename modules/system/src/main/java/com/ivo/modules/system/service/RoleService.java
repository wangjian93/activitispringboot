package com.ivo.modules.system.service;

import com.ivo.common.enums.StatusEnum;
import com.ivo.modules.system.domain.Role;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Set;

/**
 * 角色Service
 * @Author: wj
 * @Date: 2019-06-04 16:21
 * @Version 1.0
 */
public interface RoleService {

    /**
     * 根据ID获取角色
     * @param id
     * @return
     */
    Role getRoleById(Long id);

    /**
     * 获取所有角色
     * @return
     */
    List<Role> getAllRoles();

    /**
     * 分页获取角色
     * @param page
     * @param size
     * @return
     */
    Page<Role> getPageRoles(int page, int size);

    /**
     * 保存Role
     * @param role
     * @return
     */
    Role saveRole(Role role);


    /**
     * 删除Role
     * @param role
     */
    void deleteRole(Role role);






    /**
     * 根据ID获取角色数据
     * @param id 角色ID
     */
    Role getById(Long id);

    /**
     * 获取分页列表数据
     * @param example 查询实例
     * @return 返回分页数据
     */
    Page<Role> getPageList(Example<Role> example);


    /**
     * 获取用户角色列表
     * @param userid 用户ID
     */
    Set<Role> getUserOkRoleList(String userid);

    /**
     * 判断指定的用户是否存在角色
     * @param userid 用户ID
     */
    Boolean existsUserOk(String userid);


    /**
     * 获取角色列表数据
     * @param sort 排序对象
     */
    List<Role> getListBySortOk(Sort sort);

    /**
     * 角色标识是否重复
     * @param role 角色实体类
     */
    boolean repeatByName(Role role);

    /**
     * 保存角色
     * @param role 角色实体类
     */
    Role save(Role role);

    /**
     * 状态(启用，冻结，删除)/批量状态处理
     */
    Boolean updateStatus(StatusEnum statusEnum, List<Long> idList);
}
