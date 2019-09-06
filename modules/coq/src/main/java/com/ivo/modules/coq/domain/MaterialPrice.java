package com.ivo.modules.coq.domain;

import com.ivo.common.utils.StatusUtil;
import com.ivo.modules.system.model.Model;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * EE、ED关联的材料成本
 * @Author: wj
 * @Date: 2019-07-30 14:54
 * @Version 1.0
 */
@Getter
@Setter
@Entity
@Table(name = "coq_MaterialPriceMapper")
@SQLDelete(sql = "update coq_MaterialPriceMapper" + StatusUtil.sliceDelete)
@Where(clause = StatusUtil.notDelete)
public class MaterialPrice extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * PLM中获取的单号
     */
    private String orderNumber;

    /**
     * EE单
     */
    private String eeOrder;

    /**
     * ED单
     */
    private String edOrder;

    /**
     * 机种
     */
    private String project;

    /**
     * 所属厂别
     */
    private String plant;

    /**
     * 工单
     */
    private String wo;

    /**
     * 产品ID
     */
    private String productId;

    /**
     * PFCD （BEOL）
     */
    private String pfcd;

    /**
     * PFCD（FEOL）TFT
     */
    private String tft;

    /**
     * PFCD（FEOL）CF
     */
    private String cf;

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
    private Double price;

    /**
     * 工单中的费用
     */
    private Double woCost;

    /**
     * 数量
     */
    private Double quantity;

    public MaterialPrice() {}

    public MaterialPrice(String orderNumber) {
        this.orderNumber = orderNumber;
        if(StringUtils.contains(orderNumber, "EE")) {
            this.eeOrder = orderNumber;
        } else {
            this.edOrder = orderNumber;
        }
    }
}
