package com.ivo.modules.coq.domain.rework;

import com.ivo.common.utils.StatusUtil;
import com.ivo.modules.system.model.Model;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

/**
 * @author wj
 * @version 1.0
 */
@Getter
@Setter
@Entity
@Table(name = "COQ_ReworkAndScrap_StationRework")
@SQLDelete(sql = "update COQ_ReworkAndScrap_StationRework" + StatusUtil.sliceDelete)
@Where(clause = StatusUtil.notDelete)
public class StationRework extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 机种
     */
    private String project;

    /**
     * 所属厂别
     */
    private String plant;

    /**
     * 站点
     */
    private String station;

    /**
     * 描述
     */
    private String description;

    /**
     * 机台
     */
    private String machine;

    /**
     * 人工
     */
    private Double manpower;

    /**
     * 间耗材
     */
    private Double ndirectMaterial;

    /**
     * 修理
     */
    private Double  repair;

    /**
     * 能耗
     */
    private Double energy;

    /**
     * 折旧
     */
    private Double depreciation;

    /**
     * 其他
     */
    private Double other;

    /**
     * 单片费用
     */
    private Double charge;

    /**
     * 站点迭代费用
     */
    private Double totalIterationCharge;

    /**
     * 经过站点的先后顺序
     */
    private int sequence;

    /**
     * 上传日期
     */
    private Date uploadDate;
}
