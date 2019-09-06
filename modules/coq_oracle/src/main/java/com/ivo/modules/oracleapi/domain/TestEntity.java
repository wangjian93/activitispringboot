package com.ivo.modules.oracleapi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Spring data使用原生sql查询时JpaRepository需要关联Entity
 * 该Entity没有实际意义，不做业务使用
 * @Author: wj
 * @Date: 2019-07-30 13:19
 * @Version 1.0
 */
@Entity
@Table(name = "R_user")
@Getter
@Setter
public class TestEntity {
    @Id
    @Column(name = "ID_USER")
    Long id_user;
}
