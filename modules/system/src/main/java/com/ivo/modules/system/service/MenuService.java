package com.ivo.modules.system.service;

import com.ivo.modules.system.domain.Menu;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * 菜单service
 * @Author: wj
 * @Date: 2019-06-04 16:39
 * @Version 1.0
 */
public interface MenuService {

    /**
     * 根据菜单ID获取菜单数据
     * @param id
     * @return
     */
    Menu getMenuById(Long id);


    /**
     * 获取所有菜单
     * @return
     */
    List<Menu> getAllMenu();

    /**
     * 根据查询实例获取菜单列表
     * @param example 查询实例
     * @param sort 排序对象
     */
    List<Menu> getMenusByExample(Example<Menu> example, Sort sort);

}
