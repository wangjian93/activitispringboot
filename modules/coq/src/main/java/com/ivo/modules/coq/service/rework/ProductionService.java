package com.ivo.modules.coq.service.rework;

import com.ivo.modules.coq.domain.rework.ArrayProduction;
import com.ivo.modules.coq.domain.rework.CellProduction;
import com.ivo.modules.coq.domain.rework.ModuleProduction;

import java.util.List;

/**
 * @author wj
 * @version 1.0
 */
public interface ProductionService {

    List<ArrayProduction> getArrayProduction(String project);

    List<CellProduction> getCellProduction(String project);

    List<ModuleProduction> getModuleProduction(String project);

}
