package inventory.service;

import inventory.api.v1.model.IfindAllDept;
import inventory.api.v1.model.OfindAllDept;
import inventory.entity.Asset;

import java.util.List;

public interface AssetService {
    public List<Asset> findByDeptId(Integer deptId);
}
