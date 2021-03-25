package inventory.service.impl;

import inventory.api.v1.model.IfindAllDept;
import inventory.api.v1.model.OfindAllDept;
import inventory.entity.Asset;
import inventory.entity.Department;
import inventory.repository.AssetRepository;
import inventory.repository.DepartmentRepository;
import inventory.service.AssetService;
import inventory.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service("assetService")
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetRepository assetRepository;


    @Override
    public List<Asset> findByDeptId(Integer deptId) {

        List<Asset> assets = assetRepository.findByDeptId(deptId);

        return assets;
    }


}
