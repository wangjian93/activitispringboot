package com.ivo.modules.hr.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: wj
 * @Date: 2019-07-30 13:19
 * @Version 1.0
 */
@Entity
@Table(name = "R_user")
@Getter
@Setter
public class Test {

    @Id
    @Column(name = "ID_USER")
    Long id_user;
}
