package com.ivo.modules.system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ivo.modules.system.domain.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * 数据的实体类模型
 * 对数据的创建修改进行记录
 * @Author: wj
 * @Date: 2019-07-03 15:02
 * @Version 1.0
 */
@Setter
@Getter
@MappedSuperclass
public class Model extends ModelAtom {

    /**
     * 创建时间
     */
    @JsonIgnore
    @CreatedDate
    private Date createDate;

    /**
     * 创建者
     */
    @ManyToOne
    @JoinColumn(name = "creator")
    @JsonIgnore
    @CreatedBy
    private User creator;

    /**
     *  修改时间
     */
    @JsonIgnore
    @LastModifiedDate
    private Date updateDate;

    /**
     * 修改者
     */
    @ManyToOne
    @JoinColumn(name = "updater")
    @JsonIgnore
    @LastModifiedBy
    private User updater;

    /**
     * 备注
     */
    @JsonIgnore
    private String remark;
}
