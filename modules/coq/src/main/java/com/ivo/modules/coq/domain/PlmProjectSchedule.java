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
 * PLM系统中机种进度(每个阶段的时间)
 * @Author: wj
 * @Date: 2019-08-20 14:56
 * @Version 1.0
 */
@Getter
@Setter
@Entity
@Table(name = "COQ_Plm_Project_Schedule")
@SQLDelete(sql = "update COQ_Plm_Project_Schedule" + StatusUtil.sliceDelete)
@Where(clause = StatusUtil.notDelete)
public class PlmProjectSchedule extends Model {

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
     * 时间
     */
    private Date date;

    /**
     * 状态
     */
    private String status;

    public PlmProjectSchedule() {}

    public PlmProjectSchedule(String project) {
        this.project = project;
    }
}
