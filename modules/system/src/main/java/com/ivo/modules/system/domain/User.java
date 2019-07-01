package com.ivo.modules.system.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户
 * @Author: wj
 * @Date: 2019-05-31 08:05
 * @Version 1.0
 */
@Entity
@Table(name="sys_user")
@Data
public class User implements Serializable {

    @Id
    private String id;

    private String username;
    private String password;
    private String salt;
    private String picture;

    private Date createDate;

    private Date updateDate;

    private String remark;

}
