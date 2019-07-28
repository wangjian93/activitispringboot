package com.ivo.modules.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ivo.common.utils.StatusUtil;
import com.ivo.modules.system.enums.MenuTypeEnum;
import com.ivo.modules.system.model.Model;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.*;

/**
 * 菜单
 * @Author: wj
 * @Date: 2019-06-04 15:31
 * @Version 1.0
 */
@Entity
@Table(name = "sys_menu")
@Setter
@Getter
@SQLDelete(sql = "update sys_menu" + StatusUtil.sliceDelete)
@Where(clause = StatusUtil.notDelete)
public class Menu extends Model  {

    private static final long serialVersionUID = -6571323410341791753L;

    /**
     * ID
     */
    @Id
    private Long id;

    /**
     * 上级菜单
     */
    private Long pid;

    /**
     * 所有上级菜单 格式：[0],[2],[4]
     */
    private String pids;

    /**
     * 菜单名称
     */
    private String title;

    /**
     * 访问URL
     */
    private String url;

    /**
     * 对应的权限标识
     */
    private String perms;

    /**
     * 图标
     */
    private String icon;

    /**
     * 类型
     */
    private Byte type = MenuTypeEnum.MENU.getCode();

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 所属角色
     */
    @ManyToMany(mappedBy = "menus")
    @JsonIgnoreProperties(value = {"menus"})
    private Set<Role> roles = new HashSet<>(0);

    @Transient
    private Map<Long, Menu> children = new HashMap<>();

}
