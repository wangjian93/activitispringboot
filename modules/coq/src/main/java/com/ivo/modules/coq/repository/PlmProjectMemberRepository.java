package com.ivo.modules.coq.repository;

import com.ivo.modules.coq.domain.PlmProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: wj
 * @Date: 2019-08-21 10:44
 * @Version 1.0
 */
public interface PlmProjectMemberRepository extends JpaRepository<PlmProjectMember, Long> {

    List<PlmProjectMember> findByProject(String project);

    List<PlmProjectMember> findByProjectAndRole(String project, String role);

    PlmProjectMember findDistinctByProjectAndRoleAndEmployee(String project, String role, String employee);
}
