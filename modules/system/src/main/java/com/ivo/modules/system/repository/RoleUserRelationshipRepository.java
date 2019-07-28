package com.ivo.modules.system.repository;

import com.ivo.modules.system.domain.RoleUserRelationship;

import java.util.List;

/**
 * @Author: wj
 * @Date: 2019-06-04 17:02
 * @Version 1.0
 */
public interface RoleUserRelationshipRepository extends BaseRepository<RoleUserRelationship, Long> {

    /**
     * 根据user Id查询与role关联列表
     * @param userid
     * @return
     */
    List<RoleUserRelationship> findByUser_userid(String userid);
}
