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
 * 机种的阶段成本信息
 * 对应PLM中的机种的实验管理，后面会计算机种的各个阶段的成本
 * @Author: wj
 * @Date: 2019-07-25 09:36
 * @Version 1.0
 */
@Setter
@Getter
@Entity
@Table(name = "coq_projectStageCost")
@SQLDelete(sql = "update coq_projectStageCost" + StatusUtil.sliceDelete)
@Where(clause = StatusUtil.notDelete)
public class ProjectStageCost extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_fk")
    private ProjectCost projectCost;

    /**
     * 阶段
     * 在PLM中阶段和time通过"-"进行串接，如 DVT-1
     */
    private String stage;

    @OneToMany
    private List<StageDetail> stageDetails = new ArrayList<>();

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

    public ProjectStageCost() {}

    public ProjectStageCost(String stage, ProjectCost projectCost) {
        this.stage = stage;
        this.projectCost = projectCost;
    }
}
