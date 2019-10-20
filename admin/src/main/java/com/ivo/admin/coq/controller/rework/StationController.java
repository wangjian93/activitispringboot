package com.ivo.admin.coq.controller.rework;

import com.ivo.common.utils.ResultVoUtil;
import com.ivo.common.vo.ResultVo;
import com.ivo.modules.coq.cost.DoubleUtil;
import com.ivo.modules.coq.domain.rework.Station;
import com.ivo.modules.coq.domain.rework.StationRework;
import com.ivo.modules.coq.service.rework.StationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 机种站点
 * @Author: wj
 * @Date: 2019-10-13 23:37
 * @Version 1.0
 */
@Controller
@RequestMapping("coq/rework/station")
@Slf4j
public class StationController {

    @Autowired
    private StationService stationService;

    /**
     * 返回视图
     * @return
     */
    @GetMapping("/view")
    public String stationView() {
        return "/coq/rework/station";
    }

    @RequestMapping("/getStations")
    @ResponseBody
    public ResultVo getStations(String project) {
        List<Station> stationList = stationService.getStations(project);
        return ResultVoUtil.success(stationList);
    }

    @RequestMapping("/getStationReworks")
    @ResponseBody
    public ResultVo getStationReworks(String project) {
        List<StationRework> stationList = stationService.getStationReworks(project);
        return ResultVoUtil.success(stationList);
    }

    /**
     * excel文件上传
     * @param file
     * @param project
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    @ResponseBody
    public ResultVo upload(MultipartFile file, String project) {
        if(project.equals("")) {
            throw new RuntimeException("机种不为空");
        }

        List<Station> oldStations = stationService.getStations(project);
        stationService.deleteStations(oldStations);
        List<StationRework> oldStationReworks = stationService.getStationReworks(project);
        stationService.deleteStationReworks(oldStationReworks);

        List<Station> stationList = new ArrayList<>();
        List<StationRework> stationReworkList = new ArrayList<>();


        try {
            String filename = file.getOriginalFilename();
            Workbook workbook = null;

            //1、获取文件输入流
            InputStream inputStream = file.getInputStream();
            //2、获取Excel工作簿对象

            if (StringUtils.endsWith(filename, "xlsx")) {
                workbook = new XSSFWorkbook(file.getInputStream());
            } else if (StringUtils.endsWith(filename, "xls")) {
                workbook = new HSSFWorkbook(file.getInputStream());
            } else {
                throw new RuntimeException("不支持的文件格式");
            }

            //3、得到Excel工作表对象
            Sheet sheetAt = workbook.getSheetAt(0);
            //4、循环读取表格数据
            for (Row row : sheetAt) {
                //首行（即表头）不读取
                if (row.getRowNum() == 0) {
                    continue;
                }

                //读取当前行中单元格数据，索引从0开始
                Cell plantCell = row.getCell(0);
                plantCell.setCellType(CellType.STRING);
                String plant = plantCell.getStringCellValue();

                Cell stationCell = row.getCell(1);
                stationCell.setCellType(CellType.STRING);
                String station = stationCell.getStringCellValue();

                Cell descriptionCell = row.getCell(2);
                descriptionCell.setCellType(CellType.STRING);
                String description = descriptionCell.getStringCellValue();

                Cell machineCell = row.getCell(3);
                machineCell.setCellType(CellType.STRING);
                String machine = machineCell.getStringCellValue();

                Cell manpowerCell = row.getCell(4);
                manpowerCell.setCellType(CellType.NUMERIC);
                Double manpower = manpowerCell.getNumericCellValue();

                Cell ndirectMaterialCell = row.getCell(5);
                ndirectMaterialCell.setCellType(CellType.NUMERIC);
                Double ndirectMaterial = ndirectMaterialCell.getNumericCellValue();

                Cell repairCell = row.getCell(6);
                repairCell.setCellType(CellType.NUMERIC);
                Double repair = repairCell.getNumericCellValue();

                Cell energyCell = row.getCell(7);
                energyCell.setCellType(CellType.NUMERIC);
                Double energy = energyCell.getNumericCellValue();

                Cell depreciationCell = row.getCell(8);
                depreciationCell.setCellType(CellType.NUMERIC);
                Double depreciation = depreciationCell.getNumericCellValue();

                Cell otherCell = row.getCell(9);
                otherCell.setCellType(CellType.NUMERIC);
                Double other = otherCell.getNumericCellValue();

                Cell chargeCell = row.getCell(10);
                chargeCell.setCellType(CellType.NUMERIC);
                Double charge = chargeCell.getNumericCellValue();

                Cell sequenceCell = row.getCell(11);
                sequenceCell.setCellType(CellType.STRING);
                String sequenceStr = sequenceCell.getStringCellValue();
                Integer sequence = Integer.parseInt(StringUtils.isEmpty(sequenceStr) ? "0" : sequenceStr);

                Station stationObj = new Station();
                stationObj.setProject(project);
                stationObj.setPlant(plant);
                stationObj.setStation(station);
                stationObj.setDescription(description);
                stationObj.setMachine(machine);
                stationObj.setManpower(manpower);
                stationObj.setNdirectMaterial(ndirectMaterial);
                stationObj.setRepair(repair);
                stationObj.setEnergy(energy);
                stationObj.setDepreciation(depreciation);
                stationObj.setOther(other);
                stationObj.setCharge(charge);
                stationObj.setSequence(sequence);
                stationObj.setUploadDate(new Date());

                stationList.add(stationObj);
            }


            Sheet sheetAt1 = workbook.getSheetAt(1);
            Double totalIterationCharge = null;
            for (Row row : sheetAt1) {
                if (row.getRowNum() == 0) {
                    continue;
                }

                //读取当前行中单元格数据，索引从0开始
                Cell plantCell = row.getCell(0);
                plantCell.setCellType(CellType.STRING);
                String plant = plantCell.getStringCellValue();

                Cell stationCell = row.getCell(1);
                stationCell.setCellType(CellType.STRING);
                String station = stationCell.getStringCellValue();

                Cell descriptionCell = row.getCell(2);
                descriptionCell.setCellType(CellType.STRING);
                String description = descriptionCell.getStringCellValue();

                Cell machineCell = row.getCell(3);
                machineCell.setCellType(CellType.STRING);
                String machine = machineCell.getStringCellValue();

                Cell manpowerCell = row.getCell(4);
                manpowerCell.setCellType(CellType.NUMERIC);
                Double manpower = manpowerCell.getNumericCellValue();

                Cell ndirectMaterialCell = row.getCell(5);
                ndirectMaterialCell.setCellType(CellType.NUMERIC);
                Double ndirectMaterial = ndirectMaterialCell.getNumericCellValue();

                Cell repairCell = row.getCell(6);
                repairCell.setCellType(CellType.NUMERIC);
                Double repair = repairCell.getNumericCellValue();

                Cell energyCell = row.getCell(7);
                energyCell.setCellType(CellType.NUMERIC);
                Double energy = energyCell.getNumericCellValue();

                Cell depreciationCell = row.getCell(8);
                depreciationCell.setCellType(CellType.NUMERIC);
                Double depreciation = depreciationCell.getNumericCellValue();

                Cell otherCell = row.getCell(9);
                otherCell.setCellType(CellType.NUMERIC);
                Double other = otherCell.getNumericCellValue();

                Cell chargeCell = row.getCell(10);
                chargeCell.setCellType(CellType.NUMERIC);
                Double charge = chargeCell.getNumericCellValue();

                Cell sequenceCell = row.getCell(11);
                sequenceCell.setCellType(CellType.STRING);
                String sequenceStr = sequenceCell.getStringCellValue();
                Integer sequence = Integer.parseInt(StringUtils.isEmpty(sequenceStr) ? "0" : sequenceStr);

                StationRework stationRework = new StationRework();
                stationRework.setProject(project);
                stationRework.setPlant(plant);
                stationRework.setStation(station);
                stationRework.setDescription(description);
                stationRework.setMachine(machine);
                stationRework.setManpower(manpower);
                stationRework.setNdirectMaterial(ndirectMaterial);
                stationRework.setRepair(repair);
                stationRework.setEnergy(energy);
                stationRework.setDepreciation(depreciation);
                stationRework.setOther(other);
                stationRework.setCharge(charge);
                stationRework.setSequence(sequence);
                stationRework.setUploadDate(new Date());

                totalIterationCharge = DoubleUtil.sum(totalIterationCharge, charge);
                stationRework.setTotalIterationCharge(totalIterationCharge);

                if(StringUtils.equalsIgnoreCase(station, "Total")) {
                    totalIterationCharge = null;
                }

                stationReworkList.add(stationRework);
            }


            //5、关闭流
            workbook.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

        stationService.saveStations(stationList);
        stationService.saveStationReworks(stationReworkList);
        return ResultVoUtil.success();
    }

    public void readRow(Row row, Station station, StationRework stationRework) {

    }
}
