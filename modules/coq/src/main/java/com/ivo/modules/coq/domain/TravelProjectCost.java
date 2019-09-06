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
 * 出差报支费用明细
 * @Author: wj
 * @Date: 2019-09-02 01:46
 * @Version 1.0
 */
@Setter
@Getter
@Entity
@Table(name = "COQ_Travel_Project_Cost")
@SQLDelete(sql = "update COQ_Travel_Project_Cost" + StatusUtil.sliceDelete)
@Where(clause = StatusUtil.notDelete)
public class TravelProjectCost extends Model {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String project;

    private String stage;

    private Date fromDate;

    private Date toDate;

    private String employee;

    private String travelOrder;


}
