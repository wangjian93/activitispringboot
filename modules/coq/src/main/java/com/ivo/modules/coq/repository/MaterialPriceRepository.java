package com.ivo.modules.coq.repository;

import com.ivo.modules.coq.domain.MaterialPrice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: wj
 * @Date: 2019-07-30 15:08
 * @Version 1.0
 */
public interface MaterialPriceRepository extends JpaRepository<MaterialPrice, Long> {

    MaterialPrice findMaterialCostMapperByEdOrder(String edOrder);

    MaterialPrice findMaterialCostMapperByEeOrder(String eeOrder);

    MaterialPrice findDistinctByOrderNumber(String orderNumber);
}
