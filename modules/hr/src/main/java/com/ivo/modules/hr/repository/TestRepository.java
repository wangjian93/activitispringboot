package com.ivo.modules.hr.repository;

import com.ivo.modules.hr.domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @Author: wj
 * @Date: 2019-07-30 09:11
 * @Version 1.0
 */
@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

    /**
     * BOM材料成本
     * ARY 大板 ALL ED190200010 19181.11799994
     * @param material 料号
     * @param version 版本
     * @return
     */
    @Query(value = "SELECT sum(qty/base_qty*base_qty/100000*(CASE vprsv WHEN 'S' THEN stprs/peinh ELSE verpr/peinh END)) AS mtrlprice " +
            "FROM SOR.WMM_BOM_MASTER MASTER " +
            "LEFT JOIN SOR.WMM_BOM_ITEM ITEM ON MASTER.BOM_MASTER_KEY=ITEM.BOM_MASTER_KEY AND ITEM.INVALID_DT=TO_DATE('9999-12-31','YYYY-MM-DD') " +
            "LEFT JOIN EXF.SZA_PSI_MBEW c ON item.mtrl_id=c.matnr AND master.fab_id=c.bwkey " +
            "WHERE MASTER.mtrl_id=:material AND MASTER.prod_ver_id=:version " +
            "AND MASTER.fab_id='1000' " +
            "AND ITEM.Alt_Item_Ranking_Order IN ('00','01')"
            , nativeQuery = true)
    Double sqlQuery1(String material, String version);

    /**
     * BOM材料成本
     * cel 大板 去掉15 ED190600014  71.1470787525
     * @param material
     * @param version
     * @return
     */
    @Query(value = "SELECT sum(qty/base_qty*base_qty/100000*(CASE vprsv WHEN 'S' THEN stprs/peinh ELSE verpr/peinh END)) AS mtrlprice " +
            "FROM SOR.WMM_BOM_MASTER MASTER " +
            "LEFT JOIN SOR.WMM_BOM_ITEM ITEM ON MASTER.BOM_MASTER_KEY=ITEM.BOM_MASTER_KEY AND ITEM.INVALID_DT=TO_DATE('9999-12-31','YYYY-MM-DD') " +
            "LEFT JOIN EXF.SZA_PSI_MBEW c ON item.mtrl_id=c.matnr AND master.fab_id=c.bwkey " +
            "WHERE MASTER.mtrl_id=:material AND MASTER.prod_ver_id=:version " +
            "AND MASTER.fab_id='1000' " +
            "AND ITEM.Alt_Item_Ranking_Order IN ('00','01') " +
            "AND item.mtrl_id NOT LIKE '15%'"
            , nativeQuery = true)
    Double sqlQuery2(String material, String version);

    /**
     * BOM材料成本
     * Module 小板 去掉14 EE181200519 125.78325303
     * @param material
     * @param version
     * @return
     */
    @Query(value = "SELECT sum(qty/base_qty*(CASE vprsv WHEN 'S' THEN stprs/peinh ELSE verpr/peinh END)) AS mtrlprice " +
            "FROM SOR.WMM_BOM_MASTER MASTER " +
            "LEFT JOIN SOR.WMM_BOM_ITEM ITEM ON MASTER.BOM_MASTER_KEY=ITEM.BOM_MASTER_KEY AND ITEM.INVALID_DT=TO_DATE('9999-12-31','YYYY-MM-DD') " +
            "LEFT JOIN EXF.SZA_PSI_MBEW c ON item.mtrl_id=c.matnr AND master.fab_id=c.bwkey " +
            "WHERE MASTER.mtrl_id=:material AND MASTER.prod_ver_id=:version " +
            "AND MASTER.fab_id='1000' " +
            "AND ITEM.Alt_Item_Ranking_Order IN ('00','01') " +
            "AND item.mtrl_id NOT LIKE '14%'"
            , nativeQuery = true)
    Double sqlQuery3(String material, String version);

}
