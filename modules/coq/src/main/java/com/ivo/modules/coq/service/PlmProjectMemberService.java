package com.ivo.modules.coq.service;

import com.ivo.modules.coq.domain.PlmProjectMember;

import java.util.List;

/**
 * 机种的项目成员service
 * @Author: wj
 * @Date: 2019-08-21 11:11
 * @Version 1.0
 */
public interface PlmProjectMemberService {

    /**
     * @param project
     * @return
     */
    List<PlmProjectMember> getPlmMemberList(String project);

    /**
     *
     * @param project
     * @param role
     * @return
     */
    List<PlmProjectMember> getPlmMemberList(String project, String role);

    /**
     *
     * @param project
     * @param role
     * @param employee
     * @return
     */
    PlmProjectMember getPlmMember(String project, String role, String employee);

    /**
     * 同步plm机种的各阶段进度
     * @param project
     */
    List<PlmProjectMember> syncMemberFromPlm(String project);
}
