package com.ivo.modules.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ivo.common.utils.StatusUtil;
import com.ivo.modules.system.enums.UserStatusEnum;
import com.ivo.modules.system.enums.UserTypeEnum;
import com.ivo.modules.system.model.Model;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * 系统用户
 * @Author: wj
 * @Date: 2019-05-31 08:05
 * @Version 1.0
 */
@Entity
@Table(name="sys_user")
@Getter
@Setter
@SQLDelete(sql = "update sys_user" + StatusUtil.sliceDeleteForUser)
@Where(clause = StatusUtil.notDelete)
public class User extends Model {

    private static final long serialVersionUID = 2984537469333910881L;

    /**
     * 用户ID
     */
    @Id
    private String userid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    @JsonIgnore
    private String password;

    /**
     * 密码盐值
     */
    @JsonIgnore
    private String salt;

    /**
     * 头像
     */
    private String picture;

    /**
     * 用户类型
     */
    private Byte type = UserTypeEnum.EMPLOYEE.getCode();

    /**
     * 账户状态
     */
    private Byte status = UserStatusEnum.UNDISABLED.getCode();
}
