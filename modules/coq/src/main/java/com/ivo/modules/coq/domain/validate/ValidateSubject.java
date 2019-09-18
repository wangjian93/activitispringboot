package com.ivo.modules.coq.domain.validate;

import com.ivo.common.utils.StatusUtil;
import com.ivo.modules.system.model.Model;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * @Author: wj
 * @Date: 2019-09-08 17:31
 * @Version 1.0
 */
@Getter
@Setter
@Entity
@Table(name = "COQ_Validate_Item")
@SQLDelete(sql = "update COQ_Validate_Item" + StatusUtil.sliceDelete)
@Where(clause = StatusUtil.notDelete)
public class ValidateSubject extends Model {

    public static final String SUBJECT_MANPOWER = "维护费用-单片";

    public static final String SUBJECT_MAINTENANCE_COST = "人力费用-单片";

    public static final String SUBJECT_HUMITURE_ENERGY_CONSUMPTION = "温湿度机台能耗-单片";

    public static final String FORMULA_MANPOWER = "DL数量*月薪*12个月/全年实验数量";

    public static final String FORMULA_MAINTENANCE_COST = "全年机台维护费用/全年实验数量";

    public static final String FORMULA_HUMITURE_ENERGY_CONSUMPTION = "机台能耗*24小时*365天/1000*电费/全年温湿度类实验数量";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 年份
     */
    private int year;

    /**
     * 验证项目
     */
    private String subject;

    /**
     * 金额
     */
    private Double  amount;

    /**
     * 币别单位
     */
    private String currency;

    /**
     * 计算公式
     */
    private String formula;

    /**
     * 备注
     */
    private String remark;

    public ValidateSubject() {}

    public ValidateSubject(int year, String subject) {
        this.year = year;
        this.subject = subject;
    }
}
