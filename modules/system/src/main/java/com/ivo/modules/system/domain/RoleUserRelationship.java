package com.ivo.modules.system.domain;

import com.ivo.common.utils.StatusUtil;
import com.ivo.modules.system.model.Model;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * 角色用户中间表
 * @Author: wj
 * @Date: 2019-06-04 16:48
 * @Version 1.0
 */
@Entity
@Table(name = "sys_user_role")
@Data
@SQLDelete(sql = "update sys_user_role" + StatusUtil.sliceDelete)
@Where(clause = StatusUtil.notDelete)
public class RoleUserRelationship extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_fk")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_fk")
    private User user;

}
