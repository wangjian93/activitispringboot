package com.ivo.modules.coq.domain;

import com.ivo.common.utils.StatusUtil;
import com.ivo.modules.system.model.Model;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 机种成本
 * @Author: wj
 * @Date: 2019-08-30 11:19
 * @Version 1.0
 */
@Setter
@Getter
@Entity
@Table(name = "COQ_Project_Cost2")
@SQLDelete(sql = "update COQ_Project_Cost2" + StatusUtil.sliceDelete)
@Where(clause = StatusUtil.notDelete)
public class ProjectCost2 extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 机种
     */
    private String project;

    /**
     * 必要花费
     */
    private Double necessaryCost;

    /**
     * 多余花费
     */
    private Double unnecessaryCost;

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

    /**
     * 机种的阶段成本明细
     */
    @OneToMany(mappedBy = "projectCost", cascade = CascadeType.ALL)
    private List<ProjectStageCost2> projectStageCostList = new ArrayList<>();

    /**
     * 机种的二级科目成本明细
     */
    @OneToMany(mappedBy = "projectCost", cascade = CascadeType.ALL)
    private List<ProjectSecondarySubjectCost2> projectSecondarySubjectCostList = new ArrayList<>();

    public ProjectCost2() {}

    public ProjectCost2(String project) {
        this.project = project;
    }
}
