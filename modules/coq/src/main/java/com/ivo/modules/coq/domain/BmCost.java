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
 * @Author: wj
 * @Date: 2019-08-19 09:02
 * @Version 1.0
 */
@Getter
@Setter
@Entity
@Table(name = "coq_BmCost")
@SQLDelete(sql = "update coq_BmCost" + StatusUtil.sliceDelete)
@Where(clause = StatusUtil.notDelete)
public class BmCost extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String project;

    private String stage;

    private Date fromDate;

    private Date toDate;

    private Double g;

    private Double q;

    private Double z;

    private Double j;
}
