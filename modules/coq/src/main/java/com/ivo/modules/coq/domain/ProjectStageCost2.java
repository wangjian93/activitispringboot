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
 * 机种的阶段成本明细
 * @Author: wj
 * @Date: 2019-08-30 11:27
 * @Version 1.0
 */
@Setter
@Getter
@Entity
@Table(name = "COQ_Project_Stage_Cost2")
@SQLDelete(sql = "update COQ_Project_Stage_Cost2" + StatusUtil.sliceDelete)
@Where(clause = StatusUtil.notDelete)
public class ProjectStageCost2 extends Model {

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
     * 直接材料成本
     */
    private Double directMaterialCost;

    /**
     * 治工具成本
     */
    private Double toolCost;

    /**
     * 验证费用
     */
    private Double validationCost;

    /**
     * 生产费用
     */
    private Double productionCost;

    /**
     * 重工/报废费用
     */
    private Double reworkAndScrapCost;

    /**
     * 研发人员薪资费用
     */
    private Double salaryCost;

    /**
     * RMA成本
     */
    private Double rmaCost;

    /**
     * OBA成本
     */
    private Double obaCost;

    /**
     * 差旅费用
     */
    private Double travelCost;

    /**
     * 直接材料成本明细
     */
    @OneToMany(mappedBy = "projectStageCost", cascade = CascadeType.ALL)
    List<DirectMaterialCostDetail> directMaterialCostDetailList = new ArrayList<>();

    public ProjectStageCost2() {}

    public ProjectStageCost2(ProjectCost2 projectCost, String stage) {
        this.projectCost = projectCost;
        this.stage = stage;
    }
}
