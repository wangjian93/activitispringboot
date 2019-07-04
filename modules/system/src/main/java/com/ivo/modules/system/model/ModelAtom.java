package com.ivo.modules.system.model;

import com.ivo.common.enums.StatusEnum;
import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * 数据的实体类原子模型
 * 对表中数据使用逻辑删除
 * @Author: wj
 * @Date: 2019-07-03 14:50
 * @Version 1.0
 */
@Data
@MappedSuperclass
public class ModelAtom implements Serializable {

    private static final long serialVersionUID = 4964093431069570632L;

    /**
     * 逻辑删除标识
     */
    private Byte validFlag = StatusEnum.VALID.getCode();

    public ModelAtom() {
    }
}
