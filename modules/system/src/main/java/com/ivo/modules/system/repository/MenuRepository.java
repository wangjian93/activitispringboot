package com.ivo.modules.system.repository;

import com.ivo.modules.system.domain.Menu;

import java.util.List;

/**
 * @Author: wj
 * @Date: 2019-06-04 16:14
 * @Version 1.0
 */
public interface MenuRepository extends BaseRepository<Menu, Long> {

    /**
     * 查询菜单URL
     * @param url id列表
     */
    public Menu findByUrl(String url);

}
