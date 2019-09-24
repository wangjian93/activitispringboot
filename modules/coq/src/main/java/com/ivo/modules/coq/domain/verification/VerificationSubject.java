package com.ivo.modules.coq.domain.verification;

import com.ivo.common.utils.StatusUtil;
import com.ivo.modules.system.model.Model;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * 厂内验证--验证项目
 * @Author: wj
 * @Date: 2019-09-19 08:14
 * @Version 1.0
 */
@Getter
@Setter
@Entity
@Table(name = "COQ_Verification_Subject")
@SQLDelete(sql = "update COQ_Verification_Subject" + StatusUtil.sliceDelete)
@Where(clause = StatusUtil.notDelete)
public class VerificationSubject extends Model {

    /**
     * 验证项目的分类
     */
    public static final String TYPE_HUMITURE = "温湿度类";
    public static final String TYPE_UN_HUMITURE = "非温湿度类";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 验证项目
     */
    private String verificationSubject;

    /**
     * 产品类型
     */
    private String productType;

    /**
     * 验证类型
     */
    private String verificationType;

    /**
     * 机台名称
     */
    private String machineName;

    /**
     * 实验时间 （小时）
     */
    private Double verificationTime;

    /**
     * 单位
     */
    private String unit;

    /**
     * 实验条件
     */
    private String verificationCondition;

    /**
     * 分为：温湿度验证、非温湿度验证
     */
    private String type;

    /**
     * 单片电费
     */
    private Double electricityBillPer;
}
