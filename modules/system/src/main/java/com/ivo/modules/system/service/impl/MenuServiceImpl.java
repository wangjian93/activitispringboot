package com.ivo.modules.system.service.impl;

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
     * 根据ID获取菜单数据
     * @param id
     * @return
     */
    @Override
    public Menu getMenuById(Long id) {
        return menuRepository.findById(id).orElse(null);
    }

    /**
     * 获取所有菜单
     * @return
     */
    @Override
    public List<Menu> getAllMenu() {
        return menuRepository.findAll();
    }

    /**
     * 根据查询实例获取菜单
     * @param example 查询实例
     * @param sort 排序对象
     * @return
     */
    @Override
    public List<Menu> getMenusByExample(Example<Menu> example, Sort sort) {
        return menuRepository.findAll(example, sort);
    }

    /**
     * 保存菜单
     * @param menu
     * @return
     */
    @Override
    public Menu saveMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    /**
     * 删除菜单
     * @param menu
     */
    @Override
    public void deleteMenu(Menu menu) {
        menuRepository.delete(menu);
    }

    /**
     * 获取菜单的子菜单
     * @param pid
     * @return
     */
    @Override
    public List<Menu> getChildMenuByPid(Long pid) {
        return menuRepository.findByPidOrderBySort(pid);
    }
}
