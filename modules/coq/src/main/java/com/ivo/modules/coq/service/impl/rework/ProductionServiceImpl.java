package com.ivo.modules.coq.service.impl.rework;

import com.ivo.modules.coq.domain.rework.ArrayProduction;
import com.ivo.modules.coq.domain.rework.CellProduction;
import com.ivo.modules.coq.domain.rework.ModuleProduction;
import com.ivo.modules.coq.repository.rework.ArrayProductionRepository;
import com.ivo.modules.coq.repository.rework.CellProductionRepository;
import com.ivo.modules.coq.repository.rework.ModuleProductionRepository;
import com.ivo.modules.coq.service.rework.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wj
 * @version 1.0
 */
@Service
public class ProductionServiceImpl implements ProductionService {

    @Autowired
    private ArrayProductionRepository arrayProductionRepository;

    @Autowired
    private CellProductionRepository cellProductionRepository;

    @Autowired
    private ModuleProductionRepository moduleProductionRepository;

    @Override
    public List<ArrayProduction> getArrayProduction(String project) {
        return arrayProductionRepository.findByProject(project);
    }

    @Override
    public List<CellProduction> getCellProduction(String project) {
        return cellProductionRepository.findByProject(project);
    }

    @Override
    public List<ModuleProduction> getModuleProduction(String project) {
        return moduleProductionRepository.findByProject(project);
    }
}
