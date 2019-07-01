package com.ivo.modules.system.service;

import com.ivo.common.enums.StatusEnum;
import com.ivo.modules.system.domain.Menu;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: wj
 * @Date: 2019-06-04 16:39
 * @Version 1.0
 */
public interface MenuService {

    /**
     * 获取菜单列表数据
     * @param example 查询实例
     * @param sort 排序对象
     */
    List<Menu> getListByExample(Example<Menu> example, Sort sort);

    /**
     * 根据菜单ID查询菜单数据
     * @param id 菜单ID
     */
    Menu getById(Long id);

    /**
     * 获取菜单列表数据
     */
    List<Menu> getListBySortOk();

}
