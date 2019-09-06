package com.ivo.modules.coq.domain;

import com.ivo.common.utils.StatusUtil;
import com.ivo.modules.system.model.Model;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * PLM系统中机种的项目成员
 * @Author: wj
 * @Date: 2019-08-21 10:35
 * @Version 1.0
 */
@Getter
@Setter
@Entity
@Table(name = "COQ_Plm_Project_Member")
@SQLDelete(sql = "update COQ_Plm_Project_Member" + StatusUtil.sliceDelete)
@Where(clause = StatusUtil.notDelete)
public class PlmProjectMember extends Model {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    /**
     * 机种
     */
    private String project;

    /**
     * 角色
     */
    private String role;

    /**
     * 员工
     */
    private String employee;

    /**
     * 员工姓名
     */
    private String employeeName;

    public PlmProjectMember() {}

    public PlmProjectMember(String project) {
        this.project = project;
    }
}
