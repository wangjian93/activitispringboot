package com.ivo.modules.system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ivo.modules.system.domain.User;
import lombok.Data;

import javax.persistence.FetchType;
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
@Data
@MappedSuperclass
public class Model extends ModelAtom {

    private static final long serialVersionUID = -2965197988263465285L;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 创建者
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator")
    @JsonIgnore
    private User creator;

    /**
     *  修改时间
     */
    private Date updateDate;

    /**
     * 修改者
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updater")
    @JsonIgnore
    private User updater;

    /**
     * 备注
     */
    private String remark;
}
