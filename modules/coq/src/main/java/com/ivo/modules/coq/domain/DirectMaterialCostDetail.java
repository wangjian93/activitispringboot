package com.ivo.modules.coq.domain;

import com.ivo.common.utils.StatusUtil;
import com.ivo.modules.system.model.Model;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * 直接材料成本明细
 * @Author: wj
 * @Date: 2019-08-30 13:42
 * @Version 1.0
 */
@Setter
@Getter
@Entity
@Table(name = "COQ_Direct_Material_Cost_Detail")
@SQLDelete(sql = "update COQ_Direct_Material_Cost_Detail" + StatusUtil.sliceDelete)
@Where(clause = StatusUtil.notDelete)
public class DirectMaterialCostDetail extends Model {

    public static final String TYPE_Direct = "直接材料";

    public static final String TYPE_THIN = "外包薄化";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "projectStageCost_FK")
    private ProjectStageCost2 projectStageCost;

    private String type;

    private String process;

    private Double cost;
}
