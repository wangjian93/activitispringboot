package com.ivo.modules.coq.domain.validate;

import com.ivo.common.utils.StatusUtil;
import com.ivo.modules.system.model.Model;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * 验证费用的基础数据
 * @Author: wj
 * @Date: 2019-09-08 17:32
 * @Version 1.0
 */
@Getter
@Setter
@Entity
@Table(name = "COQ_Validate_Base")
@SQLDelete(sql = "update COQ_Validate_Base" + StatusUtil.sliceDelete)
@Where(clause = StatusUtil.notDelete)
public class ValidateBase extends Model {

    public static final String NAME_DL_MANPOWER = "DL_MANPOWER";

    public static final String NAME_DL_SALARY = "DL_SALARY";

    public static final String NAME_HUMITURE_ENERGY_CONSUMPTION = "HUMITURE_ENERGY_CONSUMPTION";

    public static final String NAME_ELECTRIC_CHARGE = "ELECTRIC_CHARGE";

    public static final String NAME_EXPERIMENTAL_QUANTITY = "EXPERIMENTAL_QUANTITY";

    public static final String NAME_HUMITURE_EXPERIMENTAL_QUANTITY = "HUMITURE_EXPERIMENTAL_QUANTITY";

    public static final String NAME_ORT = "ORT";

    public static final String NAME_ENGINEERING_MATERIAL_CHANGE = "ENGINEERING_MATERIAL_CHANGE";

    public static final String NAME_OTHER = "OTHER";

    public static final String NAME_NEW_PRODUCT = "NEW_PRODUCT";

    public static final String NAME_MAINTENANCE_COST = "MAINTENANCE_COST";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 年度
     */
    private int year;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String  description;

    /**
     * 数量
     */
    private Double number;

    /**
     * 单位
     */
    private String unit;

    /**
     * 排序
     */
    private int sort;

    public ValidateBase() {}

    public ValidateBase(int year, String name) {
        this.year = year;
        this.name = name;
    }
}