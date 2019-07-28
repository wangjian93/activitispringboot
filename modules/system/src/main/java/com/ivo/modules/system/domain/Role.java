package com.ivo.modules.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ivo.common.utils.StatusUtil;
import com.ivo.modules.system.model.Model;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: wj
 * @Date: 2019-06-04 15:03
 * @Version 1.0
 */
@Entity
@Table(name = "sys_role")
@Setter
@Getter
@SQLDelete(sql = "update sys_role" + StatusUtil.sliceDelete)
@Where(clause = StatusUtil.notDelete)
public class Role extends Model {

    private static final long serialVersionUID = 2337940886727405792L;

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 名称
     */
    private String title;


    /**
     * 角色拥有的菜单资源
     */
    @ManyToMany
    @JoinTable(name = "sys_role_menu",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id"))
    @JsonIgnoreProperties(value = {"roles"})
    private Set<Menu> menus = new HashSet<>(0);

}
