package com.ivo.modules.system.repository;

import com.ivo.modules.system.domain.Dict;

/**
 * @Author: wj
 * @Date: 2019-06-11 00:03
 * @Version 1.0
 */
public interface DictRepository extends BaseRepository<Dict, Long> {

    /**
     * 根据字典标识查询
     * @param name 字典标识
     * @param status 状态
     */
    Dict findByNameAndStatus(String name, Byte status);

    /**
     * 根据标识查询字典数据,且排查指定ID的字典
     * @param name 字典标识
     * @param id 字典ID
     */
    Dict findByNameAndIdNot(String name, Long id);
}
