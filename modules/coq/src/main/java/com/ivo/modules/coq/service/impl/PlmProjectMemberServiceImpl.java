package com.ivo.modules.coq.service.impl;

import com.ivo.modules.coq.domain.PlmProjectMember;
import com.ivo.modules.coq.repository.PlmProjectMemberRepository;
import com.ivo.modules.coq.rest.RestService;
import com.ivo.modules.coq.service.PlmProjectMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: wj
 * @Date: 2019-08-21 11:15
 * @Version 1.0
 */
@Service
@Slf4j
public class PlmProjectMemberServiceImpl implements PlmProjectMemberService {

    @Autowired
    private PlmProjectMemberRepository repository;

    @Autowired
    private RestService restService;

    @Override
    public List<PlmProjectMember> getPlmMemberList(String project) {
        return repository.findByProject(project);
    }

    @Override
    public List<PlmProjectMember> getPlmMemberList(String project, String role) {
        return repository.findByProjectAndRole(project, role);
    }

    @Override
    public PlmProjectMember getPlmMember(String project, String role, String employee) {
        return repository.findDistinctByProjectAndRoleAndEmployee(project, role, employee);
    }

    @Override
    public List<PlmProjectMember> syncMemberFromPlm(String project) {
        // 数据接口获取数据
        List<Map<String, String>> dataList = restService.getMembersFromPlm(project);
        // 删除原有的数据
        deletePlmMember(getPlmMemberList(project));

        // 同步新数据
        if(dataList == null) {
            return null;
        }
        List<PlmProjectMember> newList = new ArrayList<>();
        dataList.forEach(map -> {
            PlmProjectMember plmProjectMember = new PlmProjectMember(project);
            analyseMember(plmProjectMember, map);
            newList.add(plmProjectMember);
        });
        savePlmMember(newList);
        return getPlmMemberList(project);
    }

    private  void deletePlmMember(List<PlmProjectMember> list) {
        repository.deleteAll(list);
    }

    private void savePlmMember(List<PlmProjectMember> list) {
        repository.saveAll(list);
    }

    private void analyseMember(PlmProjectMember plmProjectMember, Map<String, String> map) {
        plmProjectMember.setRole(map.get("role"));
        plmProjectMember.setEmployee(map.get("user"));
        plmProjectMember.setEmployeeName(map.get("userName"));
    }
}
