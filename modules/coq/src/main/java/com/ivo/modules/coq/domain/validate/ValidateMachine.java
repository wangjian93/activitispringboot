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
 * @Date: 2019-09-08 17:27
 * @Version 1.0
 */
@Getter
@Setter
@Entity
@Table(name = "COQ_Validate_Machine")
@SQLDelete(sql = "update COQ_Validate_Machine" + StatusUtil.sliceDelete)
@Where(clause = StatusUtil.notDelete)
public class ValidateMachine extends Model {

    public static final String TYPE_OTHER = "非温湿度类";

    public static final String TYPE_HUMITURE = "温湿度类";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 仪器编号
     */
    private String machineId;

    /**
     * 仪器名称
     */
    private String machineName;

    /**
     * 厂牌
     */
    private String label;

    /**
     * 功率
     */
    private String power;

    /**
     * 型号
     */
    private String model;

    /**
     * 供应商
     */
    private String supplier;

    /**
     * 能耗
     */
    private Double energyConsumption;

    /**
     * 类型，温湿度类机台或其他
     */
    private String type;

    /**
     * 验证项目
     */
    private String subject;

}
