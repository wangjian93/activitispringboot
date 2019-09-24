package com.ivo.modules.coq.domain.verification;

import com.ivo.common.utils.StatusUtil;
import com.ivo.modules.system.model.Model;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * 厂内验证--非温湿度类机台
 * @Author: wj
 * @Date: 2019-09-19 08:57
 * @Version 1.0
 */
@Setter
@Getter
@Entity
@Table(name = "COQ_Verification_Other_Machine")
@SQLDelete(sql = "update COQ_Verification_Other_Machine" + StatusUtil.sliceDelete)
@Where(clause = StatusUtil.notDelete)
public class OtherMachine extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 机台名称
     */
    private String machineName;

    /**
     * 功率
     */
    private String powerStr;

    /**
     * 能耗
     */
    private Double power;

    /**
     * 厂牌
     */
    private String brand;

    /**
     * 机台型号
     */
    private String machineModel;

    /**
     * 供应商
     */
    private String supplier;
}
