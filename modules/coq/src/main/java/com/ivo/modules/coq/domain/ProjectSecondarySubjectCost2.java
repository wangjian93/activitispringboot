package com.ivo.modules.coq.domain;

import com.ivo.common.utils.StatusUtil;
import com.ivo.modules.system.model.Model;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * 机种二级科目成本明细
 * @Author: wj
 * @Date: 2019-08-30 11:34
 * @Version 1.0
 */
@Setter
@Getter
@Entity
@Table(name = "COQ_Project_Secondary_Subject_Cost2")
@SQLDelete(sql = "update COQ_Project_Secondary_Subject_Cost2" + StatusUtil.sliceDelete)
@Where(clause = StatusUtil.notDelete)
public class ProjectSecondarySubjectCost2 extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "projectCost_FK")
    private ProjectCost2 projectCost;

    /**
     * 阶段
     */
    private String stage;

    /**
     * 预防成本
     */
    private Double precautionCost;

    /**
     * 鉴定成本
     */
    private Double identifyCost;

    /**
     * 内损成本
     */
    private Double inLossCost;

    /**
     * 外损成本
     */
    private Double outLossCost;

    public ProjectSecondarySubjectCost2() {}

    public ProjectSecondarySubjectCost2(ProjectCost2 projectCost, String stage) {
        this.projectCost = projectCost;
        this.stage = stage;
    }
}
