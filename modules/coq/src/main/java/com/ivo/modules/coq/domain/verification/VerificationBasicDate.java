package com.ivo.modules.coq.domain.verification;

import com.ivo.common.utils.StatusUtil;
import com.ivo.modules.system.model.Model;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * 厂内验证--验证的一些基础数据
 * @Author: wj
 * @Date: 2019-09-19 09:04
 * @Version 1.0
 */
@Setter
@Getter
@Entity
@Table(name = "COQ_Verification_Basic_Date")
@SQLDelete(sql = "COQ_Verification_Basic_Date" + StatusUtil.sliceDelete)
@Where(clause = StatusUtil.notDelete)
public class VerificationBasicDate extends Model {


    // 基础数据常量
    public static final String MAN_POWER = "manPower";
    public static final String MONTHLY_SALARY = "monthlySalary";
    public static final String HUMITURE_TOTAL_POWER = "humitureTotalPower";
    public static final String ELECTRICITY_BILL_PRICE = "electricityBillPrice";
    public static final String VERIFICATION_QUANTITY = "verificationQuantity";
    public static final String HUMITURE_VERIFICATION_QUANTITY = "humitureVerificationQuantity";
    public static final String ORT_VERIFICATION_QUANTITY = "ortVerificationQuantity";
    public static final String MATERIAL_CHANGE_VERIFICATION_QUANTITY = "materialChangeVerificationQuantity";
    public static final String OTHER_VERIFICATION_QUANTITY = "otherVerificationQuantity";
    public static final String NEW_PRODUCT_VERIFICATION_QUANTITY = "newProductVerificationQuantity";
    public static final String MAINTAIN_COST = "maintainCost";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 年度
     */
    private int year;

    /**
     * 实验室DL人力（IVO+IVE）
     */
    private Double manPower;
    /**
     * 单位
     */
    private String manPower_Unit = "人";

    /**
     * 实验室DL人员月薪基数
     */
    private Double monthlySalary;
    private String monthlySalary_Unit = "RMB";

    /**
     * 温湿度实验机台总能耗
     */
    private Double humitureTotalPower;
    private String humitureTotalPower_Unit = "W";

    /**
     * 电费价格
     */
    private Double electricityBillPrice;
    private String electricityBillPrice_Unit = "RMB";

    /**
     * 全年实验数量
     */
    private Double verificationQuantity;
    private String verificationQuantity_Unit = "PCS";

    /**
     * 全年温湿度类实验数量
     */
    private Double humitureVerificationQuantity;
    private String humitureVerificationQuantity_Unit = "PCS";

    /**
     * 全年ORT验证
     */
    private Double ortVerificationQuantity;
    private String ortVerificationQuantity_Unit = "PCS";

    /**
     * 全年工程材料变更验证
     */
    private Double materialChangeVerificationQuantity;
    private String materialChangeVerificationQuantity_Unit = "PCS";

    /**
     * 全年其他验证
     */
    private Double otherVerificationQuantity;
    private String otherVerificationQuantity_Unit = "PCS";

    /**
     * 全年新产品验证
     */
    private Double newProductVerificationQuantity;
    private String newProductVerificationQuantity_Unit = "PCS";

    /**
     * 全年机台维护费用
     */
    private Double maintainCost;
    private String maintainCost_Unit = "RMB";

    /**
     * 单片人力费用
     */
    private Double manPowerCostPer;
    private String manPowerCostPer_Unit = "RMB";

    /**
     * 单片维护费用
     */
    private Double maintainCostPer;
    private String maintainCostPer_Unit = "RMB";
}
