package com.ivo.modules.system.domain;

import com.ivo.common.utils.StatusUtil;
import com.ivo.modules.system.model.Model;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * 系统用户
 * @Author: wj
 * @Date: 2019-05-31 08:05
 * @Version 1.0
 */
@Data
@Entity
@Table(name="sys_user")
@SQLDelete(sql = "update sys_user" + StatusUtil.sliceDelete)
@Where(clause = StatusUtil.notDelete)
public class User extends Model {

    /**
     * 用户ID
     */
    @Id
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 密码盐值
     */
    private String salt;

    /**
     * 头像
     */
    private String picture;

    /**
     * 用户类型
     */
    private Byte type;
}
