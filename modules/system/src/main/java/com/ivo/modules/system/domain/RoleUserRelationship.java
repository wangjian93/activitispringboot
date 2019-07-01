package com.ivo.modules.system.domain;

import com.ivo.common.enums.StatusEnum;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: wj
 * @Date: 2019-06-04 16:48
 * @Version 1.0
 */
@Entity
@Table(name = "sys_user_role")
@Data
public class RoleUserRelationship implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_fk")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_fk")
    private User user;

    private Date createDate;
    private Date updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator")
    private User creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updater")
    private User updater;

    private Byte validFlag = StatusEnum.VALID.getCode();

}
