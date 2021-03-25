package inventory.service;

import inventory.api.v1.model.*;
import inventory.entity.Asset;
import inventory.entity.InventoryDetail;

public interface InventoryDetailService {

    public InventoryDetail save(Integer inventoryMasterId, Asset asset, String userName);

    public OeditDetail editDetail(IeditDetail ieditDetail, String userName);

    public OdeleteDetail deleteDetail(IdeleteDetail ideleteDetail, String userName);

    public OsearchDetail searchDetail(IsearchDetail isearchDetail);

}
