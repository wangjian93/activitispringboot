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
 * 模组生产信息
 * @author wj
 * @version 1.0
 */
@Getter
@Setter
@Entity
@Table(name = "COQ_ReworkAndScrap_ModuleProduction")
@SQLDelete(sql = "update COQ_ReworkAndScrap_ModuleProduction" + StatusUtil.sliceDelete)
@Where(clause = StatusUtil.notDelete)
public class ModuleProduction extends Model {

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

    private Date fabDate;

    /**
     * 站点
     */
    private String station;

    /**
     * 重工或报废
     */
    private String cate;

    /**
     * 数量
     */
    private Double  quantity;

    /**
     * 工单
     */
    private String  woId;

    private String  prodModelId;

    private String prodId;

    private String scrpStation;
}
