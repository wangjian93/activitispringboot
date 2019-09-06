package com.ivo.modules.coq.domain;

import com.ivo.common.utils.StatusUtil;
import com.ivo.modules.coq.enums.StageTypeEnum;
import com.ivo.modules.system.model.Model;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Date;

/**
 * PLM系统中机种的阶段信息
 * @Author: wj
 * @Date: 2019-08-20 14:05
 * @Version 1.0
 */
@Getter
@Setter
@Entity
@Table(name = "COQ_Plm_Project_Stage")
@SQLDelete(sql = "update COQ_Plm_Project_Stage" + StatusUtil.sliceDelete)
@Where(clause = StatusUtil.notDelete)
public class PlmProjectStage extends Model implements Comparable<PlmProjectStage> {

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
     * 制程（可能为CellL、Module、Array）
     */
    private String process;

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
     * 投入时间
     */
    private Date inDate;

    /**
     * 产出时间
     */
    private Date outDate;

    /**
     * EE/ED单号
     */
    private String edOrEeOrOrderNumber;

    /**
     * 是否完成
     */
    private boolean complete;

    public PlmProjectStage() {}

    public PlmProjectStage(String project) {
        this.project = project;
    }

    public PlmProjectStage(String project, String stage) {
        this.project = project;
        this.stage = stage;
    }

    /**
     * 排序：NPRB < Design < EVT < DVT < PVT < other
     * @param o
     * @return
     */
    @Override
    public int compareTo(@NotNull PlmProjectStage o) {
        return priorityLevel(o.getStage()) - priorityLevel(this.getStage());
    }

    /**
     * 获取阶段的优先级
     * @param stage
     * @return
     */
    public static int priorityLevel(String stage) {
        int prefix = 1000;
        if(StringUtils.startsWith(stage, StageTypeEnum.NPRB.getStage())) {
            prefix = 9000;
        } else if(StringUtils.startsWith(stage, StageTypeEnum.DESIGN.getStage())) {
            prefix = 8000;
        } else if(StringUtils.startsWith(stage, StageTypeEnum.EVT.getStage())) {
            prefix = 7000;
        } else if(StringUtils.startsWith(stage, StageTypeEnum.DVT.getStage())) {
            prefix = 6000;
        } else if(StringUtils.startsWith(stage, StageTypeEnum.PVT.getStage())) {
            prefix = 5000;
        }

        int suffix = 0;
        if(stage.indexOf("-") > -1) {
            suffix = Integer.parseInt(stage.substring(stage.indexOf("-")+1));
        }
        return prefix-suffix;
    }
}
