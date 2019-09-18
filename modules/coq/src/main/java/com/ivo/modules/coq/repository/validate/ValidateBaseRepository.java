package com.ivo.modules.coq.repository.validate;

import com.ivo.modules.coq.domain.validate.ValidateBase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: wj
 * @Date: 2019-09-08 18:07
 * @Version 1.0
 */
public interface ValidateBaseRepository extends JpaRepository<ValidateBase, Long> {

    ValidateBase findDistinctByYearAndName(int year, String name);

    List<ValidateBase> findByOrderBySortAsc();
}
