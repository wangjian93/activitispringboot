package com.ivo.modules.coq.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 机种的阶段信息
 * 对应PLM中的机种的实验管理，后面会计算机种的各个阶段的成本
 * @Author: wj
 * @Date: 2019-07-25 09:07
 * @Version 1.0
 */
public class Phase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    /**
     * 阶段
     */
    private String stage;

    private String process;

    private Date inDate;

    private Date outDate;

    private int inQuantity;

    private int  outQuantity;

    private boolean complete;

    /**
     *
     */
    private String orderNumber;

}
