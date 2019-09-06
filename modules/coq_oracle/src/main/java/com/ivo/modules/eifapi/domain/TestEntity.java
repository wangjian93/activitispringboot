package com.ivo.modules.eifapi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: wj
 * @Date: 2019-08-19 08:42
 * @Version 1.0
 */
@Entity
@Table(name = "test")
@Getter
@Setter
public class TestEntity {

    @Id
    @Column(name = "ordernumber")
    String ordernumber;
}
