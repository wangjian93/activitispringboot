package com.ivo.modules.system.domain;

import com.ivo.common.enums.StatusEnum;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 数据字典
 * @Author: wj
 * @Date: 2019-06-10 23:58
 * @Version 1.0
 */
@Entity
@Table(name="sys_dict")
@Data
public class Dict implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String title;
    private Byte type;
    private String value;
    private String remark;

    private Date createDate;
    private Date updateDate;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="creator")
    private User creator;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="updater")
    private User updater;

    private Byte status = StatusEnum.VALID.getCode();

}
