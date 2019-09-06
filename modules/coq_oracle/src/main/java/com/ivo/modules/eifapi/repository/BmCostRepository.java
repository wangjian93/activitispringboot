package com.ivo.modules.eifapi.repository;

import com.ivo.modules.eifapi.domain.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: wj
 * @Date: 2019-08-19 00:59
 * @Version 1.0
 */
public interface BmCostRepository extends JpaRepository<TestEntity, Long> {

    /**
     * 固定资产
     * @param fromDate
     * @param toDate
     * @param project
     * @return
     */
    @Query(value = "SELECT sum(amount) AS amount FROM (" +
            "SELECT a.TrackingNumber,b.UnitPrice*b.Quantity AS amount FROM MM_M_PurchaseRequisition a,MM_D_PurchaseRequisitionItem b,MM_O_MaterialGroup c WHERE a.PurchaseRequisition_ID=b.PurchaseRequisition_FK AND a.ValidFlag=1 AND b.ValidFlag=1 AND c.ValidFlag=1 AND a.OrderTask_FK='980' AND a.DateOfCreate>='2018-03-19' AND a.DateOfCreate<='2018-05-10' AND b.BudgetNumber LIKE 'N1408-R0%' AND b.BudgetType='资本' AND b.MaterialGroup_FK=c.MaterialGroup_ID AND c.MaterialGroupName LIKE '%固定资产%') t", nativeQuery = true)
    Double test1(Date fromDate, Date toDate, String project);

    /**
     * 其他费用
     * @param fromDate
     * @param toDate
     * @param project
     * @return
     */
    @Query(value = "SELECT sum(amount) AS amount FROM (" +
            "SELECT a.TrackingNumber,b.UnitPrice*b.Quantity AS amount FROM MM_M_PurchaseRequisition a,MM_D_PurchaseRequisitionItem b,MM_O_MaterialGroup c WHERE a.PurchaseRequisition_ID=b.PurchaseRequisition_FK AND a.ValidFlag=1 AND b.ValidFlag=1 AND c.ValidFlag=1 AND a.OrderTask_FK='980' AND a.DateOfCreate>='2018-03-19' AND a.DateOfCreate<='2018-05-10' AND b.BudgetNumber LIKE 'N1408-R0%' AND b.BudgetType='其他' AND b.MaterialGroup_FK=c.MaterialGroup_ID AND c.MaterialGroupName NOT LIKE '%费用-委外加工费%' AND c.MaterialGroupName NOT LIKE '%服务-检测%' AND b.Material_FK NOT IN (" +
            "SELECT Material_ID FROM MM_O_Material WHERE ValidFlag=1 AND MaterialGroup_FK='002')) t", nativeQuery = true)
    Double test2(Date fromDate, Date toDate, String project);

    /**
     * 直接材料成本（外包薄化）
     * @param fromDate
     * @param toDate
     * @param project
     * @return
     */
    @Query(value = "SELECT sum(amount) AS amount FROM (SELECT a.TrackingNumber,b.UnitPrice*b.Quantity AS amount FROM MM_M_PurchaseRequisition a,MM_D_PurchaseRequisitionItem b,MM_O_MaterialGroup c WHERE a.PurchaseRequisition_ID=b.PurchaseRequisition_FK AND a.ValidFlag=1 AND b.ValidFlag=1 AND c.ValidFlag=1 AND a.OrderTask_FK='980' AND a.DateOfCreate>='2018-03-19' AND a.DateOfCreate<='2018-05-10' AND b.BudgetNumber LIKE 'N1408-R0%' AND b.BudgetType='其他' AND b.MaterialGroup_FK=c.MaterialGroup_ID AND (c.MaterialGroupName NOT LIKE '%费用-委外加工费%' OR b.Material_FK IN (SELECT Material_ID FROM MM_O_Material WHERE ValidFlag=1 AND MaterialGroup_FK='002'))) t", nativeQuery = true)
    Double test3(Date fromDate, Date toDate, String project);

    /**
     * 检测
     * @param fromDate
     * @param toDate
     * @param project
     * @return
     */
    @Query(value = "SELECT sum(amount) AS amount FROM (SELECT a.TrackingNumber,b.UnitPrice*b.Quantity AS amount FROM MM_M_PurchaseRequisition a,MM_D_PurchaseRequisitionItem b,MM_O_MaterialGroup c WHERE a.PurchaseRequisition_ID=b.PurchaseRequisition_FK AND a.ValidFlag=1 AND b.ValidFlag=1 AND c.ValidFlag=1 AND a.OrderTask_FK='980' AND a.DateOfCreate>='2018-03-19' AND a.DateOfCreate<='2018-05-10' AND b.BudgetNumber LIKE 'N1408-R0%' AND b.BudgetType='其他' AND b.MaterialGroup_FK=c.MaterialGroup_ID AND c.MaterialGroupName LIKE '%服务-检测%') t", nativeQuery = true)
    Double test4(Date fromDate, Date toDate, String project);



    @Query(value = "SELECT a.TrackingNumber,b.UnitPrice*b.Quantity AS amount FROM MM_M_PurchaseRequisition a,MM_D_PurchaseRequisitionItem b,MM_O_MaterialGroup c " +
            "WHERE a.PurchaseRequisition_ID=b.PurchaseRequisition_FK AND a.ValidFlag=1 AND b.ValidFlag=1 AND c.ValidFlag=1 AND a.OrderTask_FK='980' " +
            "AND a.DateOfCreate>=:fromDate AND a.DateOfCreate<=:toDate " +
            "AND b.BudgetNumber LIKE :project " +
            "AND b.BudgetType='资本' AND b.MaterialGroup_FK=c.MaterialGroup_ID AND c.MaterialGroupName LIKE '%固定资产%'", nativeQuery = true)
    List<Map<String, Object>> getToolCapital(Date fromDate, Date toDate, String project);

    @Query(value = "SELECT a.TrackingNumber,b.UnitPrice*b.Quantity AS amount FROM MM_M_PurchaseRequisition a,MM_D_PurchaseRequisitionItem b,MM_O_MaterialGroup c " +
            "WHERE a.PurchaseRequisition_ID=b.PurchaseRequisition_FK AND a.ValidFlag=1 AND b.ValidFlag=1 AND c.ValidFlag=1 AND a.OrderTask_FK='980' " +
            "AND a.DateOfCreate>=:fromDate AND a.DateOfCreate<=:toDate " +
            "AND b.BudgetNumber LIKE :project " +
            "AND b.BudgetType='其他' AND b.MaterialGroup_FK=c.MaterialGroup_ID AND c.MaterialGroupName NOT LIKE '%费用-委外加工费%' AND c.MaterialGroupName NOT LIKE '%服务-检测%' " +
            "AND b.Material_FK NOT IN (SELECT Material_ID FROM MM_O_Material WHERE ValidFlag=1 AND MaterialGroup_FK='002')", nativeQuery = true)
    List<Map<String, Object>> getToolOther(Date fromDate, Date toDate, String project);

    @Query(value = "SELECT a.TrackingNumber,b.UnitPrice*b.Quantity AS amount FROM MM_M_PurchaseRequisition a,MM_D_PurchaseRequisitionItem b,MM_O_MaterialGroup c " +
            "WHERE a.PurchaseRequisition_ID=b.PurchaseRequisition_FK AND a.ValidFlag=1 AND b.ValidFlag=1 AND c.ValidFlag=1 AND a.OrderTask_FK='980' " +
            "AND a.DateOfCreate>=:fromDate AND a.DateOfCreate<=:toDate " +
            "AND b.BudgetNumber LIKE :project " +
            "AND b.BudgetType='其他' " +
            "AND b.MaterialGroup_FK=c.MaterialGroup_ID AND (c.MaterialGroupName NOT LIKE '%费用-委外加工费%' OR b.Material_FK IN (SELECT Material_ID FROM MM_O_Material WHERE ValidFlag=1 AND MaterialGroup_FK='002'))", nativeQuery = true)
    List<Map<String, Object>> getDirectMaterial(Date fromDate, Date toDate, String project);

    @Query(value = "SELECT a.TrackingNumber,b.UnitPrice*b.Quantity AS amount FROM MM_M_PurchaseRequisition a,MM_D_PurchaseRequisitionItem b,MM_O_MaterialGroup c " +
            "WHERE a.PurchaseRequisition_ID=b.PurchaseRequisition_FK AND a.ValidFlag=1 AND b.ValidFlag=1 AND c.ValidFlag=1 AND a.OrderTask_FK='980' " +
            "AND a.DateOfCreate>=:fromDate AND a.DateOfCreate<=:toDate " +
            "AND b.BudgetNumber LIKE :project " +
            "AND b.BudgetType='其他' " +
            "AND b.MaterialGroup_FK=c.MaterialGroup_ID AND c.MaterialGroupName LIKE '%服务-检测%'", nativeQuery = true)
    List<Map<String, Object>> getValidation(Date fromDate, Date toDate, String project);
}
