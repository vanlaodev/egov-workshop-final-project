package inventory.repository;

import inventory.entity.Asset;
import inventory.entity.InventoryMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, Integer> {

    public List<Asset> findByDeptId(@Param("deptId") Integer deptId);



}
