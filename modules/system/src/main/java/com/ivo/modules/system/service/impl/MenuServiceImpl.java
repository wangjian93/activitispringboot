package com.ivo.modules.system.service.impl;

import com.ivo.common.enums.StatusEnum;
import com.ivo.modules.system.domain.Menu;
import com.ivo.modules.system.repository.MenuRepository;
import com.ivo.modules.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wj
 * @Date: 2019-06-05 10:31
 * @Version 1.0
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    /**
     * 根据菜单ID查询菜单数据
     * @param id 菜单ID
     */
    @Override
    public Menu getById(Long id) {
        if (id == 0L){
            return new Menu(id, "顶级菜单", "");
        }
        return menuRepository.findById(id).orElse(null);
    }

    /**
     * 获取菜单列表数据
     */
    @Override
    public List<Menu> getListBySortOk() {
        Sort sort = new Sort(Sort.Direction.ASC, "type", "sort");
        return menuRepository.findAll(sort);
    }

    /**
     * 获取菜单列表数据
     * @param example 查询实例
     * @param sort 排序对象
     */
    @Override
    public List<Menu> getListByExample(Example<Menu> example, Sort sort) {
        return menuRepository.findAll(example, sort);
    }
}
