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
 * 机种阶段的其他的一些信息
 * @Author: wj
 * @Date: 2019-07-25 09:37
 * @Version 1.0
 */
@Getter
@Setter
@Entity
@Table(name = "coq_StageDetail")
@SQLDelete(sql = "update coq_StageDetail" + StatusUtil.sliceDelete)
@Where(clause = StatusUtil.notDelete)
public class StageDetail extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "projectStage_fk")
    private ProjectStageCost projectStageCost;

    /**
     * （Array、Module、Cell）
     */
    private String process;

    /**
     * 投入时间
     */
    private Date inDate;

    /**
     * 产出时间
     */
    private Date outDate;

    /**
     * 投入数量
     */
    private Double inQuantity;

    /**
     * 产出数量
     */
    private Double outQuantity;

    /**
     * 单位
     */
    private String units;

    /**
     * 是否完成
     */
    private boolean complete;

    /**
     * 实验申请单（ED、EE、OEE）
     */
    private String orderNumber;

    public StageDetail() {}

    public StageDetail(ProjectStageCost projectStageCost) {
        this.projectStageCost = projectStageCost;
    }
}
