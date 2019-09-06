package com.ivo.modules.oracleapi.repository;

import com.ivo.modules.oracleapi.domain.TestEntity;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

/**
 * sql查询boom材料成本数据
 * @Author: wj
 * @Date: 2019-07-30 14:25
 * @Version 1.0
 */
public interface BoomCostRepository extends JpaRepository<TestEntity, Long> {

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
    Double getPriceForArray(String material, String version);

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
    Double getPriceForCell(String material, String version);

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
    Double getPriceForModule(String material, String version);







    @Query(value = "SELECT * from sapeif.zmap_mat a " +
            "where a.prodid =:productId"
            , nativeQuery = true)
    Map getMaterialAndVersion(String productId);


//    @Query(value = "select sum( case BWART when '261' then dmbtr else 0-dmbtr end) " +
//            "from exf.sap_aufm a " +
//            "where aufnr = :wo " +
//            "AND BWART IN ( '261','262')"
//            , nativeQuery = true)

    @Query(value = "SELECT sum(dmbtr) FROM (" +
            "SELECT a.matnr," +
            "sum(CASE BWART WHEN '261' THEN a.menge*c.price WHEN '262' THEN 0-a.menge*c.price WHEN 'Z91' THEN 0-a.menge*c.price ELSE a.menge*c.price END) AS dmbtr," +
            "sum(CASE BWART WHEN '261' THEN a.menge WHEN '262' THEN 0-a.menge WHEN 'Z91' THEN 0-a.menge ELSE a.menge END) AS menge " +
            "FROM exf.sap_aufm a LEFT JOIN (" +
            "SELECT matnr," +
            "sum(CASE BWART WHEN '261' THEN dmbtr WHEN '262' THEN 0-dmbtr END) AS dmbtr," +
            "sum(CASE BWART WHEN '261' THEN menge WHEN '262' THEN 0-menge END) AS menge," +
            "sum(CASE BWART WHEN '261' THEN dmbtr WHEN '262' THEN 0-dmbtr END)/sum(CASE BWART WHEN '261' THEN menge WHEN '262' THEN 0-menge END) AS price " +
            "FROM exf.sap_aufm a WHERE aufnr=:wo AND BWART IN ('261','262') GROUP BY matnr) " +
            "c ON a.matnr=c.matnr WHERE aufnr=:wo AND BWART IN ('261','262','Z91','Z92') AND a.matnr NOT LIKE '14%' " +
            "GROUP BY a.matnr" +
            ")"
        , nativeQuery =  true)
    Double getWoCost(String wo);



    @Query(value = "SELECT sum(qty/base_qty*base_qty/100000*(CASE vprsv WHEN 'S' THEN stprs/peinh ELSE verpr/peinh END)) AS mtrlprice " +
            "FROM SOR.WMM_BOM_MASTER MASTER " +
            "LEFT JOIN SOR.WMM_BOM_ITEM ITEM ON MASTER.BOM_MASTER_KEY=ITEM.BOM_MASTER_KEY AND ITEM.INVALID_DT=TO_DATE('9999-12-31','YYYY-MM-DD') " +
            "LEFT JOIN EXF.SZA_PSI_MBEW c ON item.mtrl_id=c.matnr AND master.fab_id=c.bwkey " +
            "LEFT JOIN SAPEIF.ZMAP_MAT M ON MASTER.mtrl_id = M.Matnr AND MASTER.PROD_VER_ID = M.VERID " +
            "WHERE m.prodid =:productId " +
            "AND MASTER.fab_id='1000' " +
            "AND ITEM.Alt_Item_Ranking_Order IN ('00','01')"
            , nativeQuery = true)
    Double getPriceForArray(String productId);


    @Query(value = "SELECT sum(qty/base_qty*base_qty/100000*(CASE vprsv WHEN 'S' THEN stprs/peinh ELSE verpr/peinh END)) AS mtrlprice " +
            "FROM SOR.WMM_BOM_MASTER MASTER " +
            "LEFT JOIN SOR.WMM_BOM_ITEM ITEM ON MASTER.BOM_MASTER_KEY=ITEM.BOM_MASTER_KEY AND ITEM.INVALID_DT=TO_DATE('9999-12-31','YYYY-MM-DD') " +
            "LEFT JOIN EXF.SZA_PSI_MBEW c ON item.mtrl_id=c.matnr AND master.fab_id=c.bwkey " +
            "LEFT JOIN SAPEIF.ZMAP_MAT M ON MASTER.mtrl_id = M.Matnr AND MASTER.PROD_VER_ID = M.VERID " +
            "WHERE m.prodid =:productId " +
            "AND MASTER.fab_id='1000' " +
            "AND ITEM.Alt_Item_Ranking_Order IN ('00','01') " +
            "AND item.mtrl_id NOT LIKE '15%'"
            , nativeQuery = true)
    Double getPriceForCell(String productId);
}
