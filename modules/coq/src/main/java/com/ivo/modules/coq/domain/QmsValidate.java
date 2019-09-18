package com.ivo.modules.coq.domain;

import com.ivo.common.utils.StatusUtil;
import com.ivo.modules.system.model.Model;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

/**
 * QMS机种阶段的验证数据
 * @Author: wj
 * @Date: 2019-09-09 01:50
 * @Version 1.0
 */
@Getter
@Setter
@Entity
@Table(name = "COQ_Qms_Validate")
@SQLDelete(sql = "update COQ_Qms_Validate" + StatusUtil.sliceDelete)
@Where(clause = StatusUtil.notDelete)
public class QmsValidate extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 机种
     */
    private String project;

    /**
     * 阶段
     */
    private String stage;

    /**
     * 验证类型
     */
    private String validateType;

    /**
     * 验证项目
     */
    private String validateSubject;

    /**
     * 实验条件
     */
    private String testCondition;

    /**
     * 数量
     */
    private Double quantity;

    /**
     * 单位
     */
    private String unit;

    /**
     * 开始时间
     */
    private Date fromDate;

    /**
     * 结束时间
     */
    private Date toDate;






}
