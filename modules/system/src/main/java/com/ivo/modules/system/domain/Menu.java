package com.ivo.modules.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ivo.common.enums.StatusEnum;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * @Author: wj
 * @Date: 2019-06-04 15:31
 * @Version 1.0
 */
@Entity
@Table(name = "sys_menu")
@Data
public class Menu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pid;
    private String pids;
    private String title;
    private String url;
    private String perms;
    private String icon;
    private Byte type;
    private Integer sort;
    private String remark;

    private Date createDate;
    private Date updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator")
    @JsonIgnore
    private User creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updater")
    @JsonIgnore
    private User updater;

    private Byte validFlag = StatusEnum.VALID.getCode();

    @ManyToMany(mappedBy = "menus")
    @JsonIgnore
    private Set<Role> roles = new HashSet<>(0);

    @Transient
    @JsonIgnore
    private Map<Long, Menu> children = new HashMap<>();

    public Menu() {
    }

    public Menu(Long id, String title, String pids) {
        this.id = id;
        this.title = title;
        this.pids = pids;
    }

    public void setPids(String pids) {
        if (pids.startsWith(",")){
            pids = pids.substring(1);
        }
        this.pids = pids;
    }
}
