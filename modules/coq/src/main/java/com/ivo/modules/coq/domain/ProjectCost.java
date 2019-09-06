package com.ivo.modules.coq.domain;

import com.ivo.common.constant.StatusConst;
import com.ivo.common.utils.StatusUtil;
import com.ivo.modules.coq.cost.AmountFormatUtils;
import com.ivo.modules.system.model.Model;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 机种的成本
 * @Author: wj
 * @Date: 2019-07-25 09:16
 * @Version 1.0
 */
@Getter
@Setter
@Entity
@Table(name = "coq_projectCost")
@SQLDelete(sql = "update coq_projectCost set valid_Flag=" + StatusConst.DELETE + " WHERE project=?")
@Where(clause = StatusUtil.notDelete)
public class ProjectCost extends Model {

    @Id
    private String projectName;

    @OneToMany(mappedBy = "projectCost", cascade = CascadeType.ALL)
    private List<ProjectStageCost> projectStageCosts = new ArrayList<>();

    /**
     * 必要花费
     * 预防成本 + 鉴定成本
     */
    private Double necessaryCost;

    /**
     * 多余花费
     * 内损成本 + 外损成本
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

    public ProjectCost() {}

    public ProjectCost(String projectName) {
        this.projectName = projectName;
    }

    /**
     * 格式化数字为千分位显示；
     * @param d 要格式化的数字
     * @return
     */
    public String fmtMicrometer(Double d) {
        return new AmountFormatUtils().fmtThousands(d);
    }
}
