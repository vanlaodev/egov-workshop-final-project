package inventory.repository;

import inventory.entity.InventoryDetail;
import inventory.entity.InventoryMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryDetailRepository extends JpaRepository<InventoryDetail, Integer> {

    public List<InventoryDetail> findByInventoryMasterId(@Param("inventoryMasterId") Integer inventoryMasterId);

    @Query("select d from InventoryDetail d where d.inventoryMasterId = :inventoryMasterId and d.userName = :userName")
    public List<InventoryDetail> findByInventoryMasterIdAndUserName(@Param("inventoryMasterId") Integer inventoryMasterId, @Param("userName") String userName);

    @Query("select d from InventoryDetail d where d.userName = :userName")
    public List<InventoryDetail> findByUserName(@Param("userName") String userName);

}
