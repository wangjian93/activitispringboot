package com.ivo.modules.coq.domain.verification;

import com.ivo.common.utils.StatusUtil;
import com.ivo.modules.system.model.Model;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * 厂内验证费用
 * @Author: wj
 * @Date: 2019-09-19 10:50
 * @Version 1.0
 */
@Setter
@Getter
@Entity
@Table(name = "COQ_Verification_Plant_Cost")
@SQLDelete(sql = "COQ_Verification_Plant_Cost" + StatusUtil.sliceDelete)
@Where(clause = StatusUtil.notDelete)
public class InPlantVerificationCost extends Model {

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
     * 验证项目
     */
    private String verificationSubject;

    /**
     * 验证类型
     */
    private String verificationType;

    /**
     * 实验条件
     */
    private String verificationCondition;

    /**
     * 数量
     */
    private Double quantity;


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
     * 分为：温湿度验证、非温湿度验证
     */
    private String type;

    /**
     * 单片人力费用
     */
    private Double manPowerCostPer;

    /**
     * 单片维护费用
     */
    private Double maintainCostPer;

    /**
     * 单片电费
     */
    private Double electricityBillPer;

    /**
     * 人力费用
     */
    private Double manPowerCost;

    /**
     * 维护费用
     */
    private Double maintainCost;

    /**
     * 电费
     */
    private Double electricityBill;

    public InPlantVerificationCost() {}

    public InPlantVerificationCost(String project) {
        this.project = project;
    }


}
