package inventory.repository;

import inventory.entity.InventoryMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryMasterRepository extends JpaRepository<InventoryMaster, Integer> {

    public List<InventoryMaster> findByDeptId(@Param("deptId") Integer deptId);

    @Query("select m from InventoryMaster m where m.title like %:title%")
    public List<InventoryMaster> findByTitle(@Param("title") String title);

    @Query("select m from InventoryMaster m where m.deptId = :deptId and m.title like %:title%")
    public List<InventoryMaster> findByDeptIdAndTitle(@Param("deptId") Integer deptId, @Param("title") String title);


    @Query("select m from InventoryMaster m inner join InventoryDetail d on m.id = d.inventoryMasterId where d.userName = :userName")
    public List<InventoryMaster> searchMasterByInventoryHolder(@Param("userName") String userName);

}
