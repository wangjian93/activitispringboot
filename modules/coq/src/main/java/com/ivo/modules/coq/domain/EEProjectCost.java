package com.ivo.modules.coq.domain;

import com.ivo.common.utils.StatusUtil;
import com.ivo.modules.system.model.Model;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * EE/ED单中计算机种的直接材料成本，根据EE与ED得到ProductId、工单，从BOM和工单中得到数据
 * @Author: wj
 * @Date: 2019-08-23 09:44
 * @Version 1.0
 */
@Setter
@Getter
@Entity
@Table(name = "COQ_EE_Project_Cost")
@SQLDelete(sql = "update COQ_EE_Project_Cost" + StatusUtil.sliceDelete)
@Where(clause = StatusUtil.notDelete)
public class EEProjectCost extends Model {

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
     * 可以看成厂别
     */
    private String process;

    /**
     * PLM中申请单号
     */
    private String orderNumber;

    /**
     * 厂别
     */
    private String factory;

    /**
     * ED单
     */
    private String edOrder;

    /**
     * EE单
     */
    private String eeOrder;

    /**
     * 产品ID
     */
    private String prodId;

    /**
     * PFCD （BEOL）
     */
    private String pfcd;

    /**
     * PFCD（FEOL）TFT
     */
    private String pfcd_tft;

    /**
     * PFCD（FEOL）CF
     */
    private String pfcd_cf;

    /**
     * 工单
     */
    private String wo;

    /**
     * 料号
     */
    private String material;

    /**
     * 料号版本
     */
    private String version;

    /**
     * 料号价格
     */
    private Double materialPrice;

    /**
     * 工单中的费用
     */
    private Double woCost;

    public EEProjectCost() {}

    public EEProjectCost(String project, String stage, String process, String orderNumber) {
        this.project = project;
        this.stage = stage;
        this.process = process;
        this.orderNumber = orderNumber;
    }
}
