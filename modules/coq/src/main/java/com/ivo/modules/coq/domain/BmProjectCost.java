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
 * BM系统中机种的治工具、直接材料成本（外包薄化）、验证费用
 * @Author: wj
 * @Date: 2019-08-22 10:11
 * @Version 1.0
 */
@Setter
@Getter
@Entity
@Table(name = "COQ_Bm_Project_Cost")
@SQLDelete(sql = "update COQ_Bm_Project_Cost" + StatusUtil.sliceDelete)
@Where(clause = StatusUtil.notDelete)
public class BmProjectCost extends Model {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
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
     * 成本类型
     */
    private String costType;

    /**
     * 预算方式
     */
    private String budgetType;

    /**
     * 接种阶段计算BM的时间区间
     */
    private Date fromDate;

    private Date toDate;

    /**
     * BM中PR的单号
     */
    private String prOrderNumber;

    /**
     * 对应PR的金额
     */
    private Double amount;

    public BmProjectCost() {}

    public BmProjectCost(String project, String stage, String costType, String budgetType) {
        this.project = project;
        this.stage = stage;
        this.costType = costType;
        this.budgetType = budgetType;
    }
}
