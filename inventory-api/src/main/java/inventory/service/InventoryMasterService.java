package inventory.service;

import inventory.api.v1.model.*;

public interface InventoryMasterService {
    public OcreateMaster createMaster(IcreateMaster icreateMaster, String userName);

    public OsearchMaster searchMaster(IsearchMaster isearchMaster);

    public OsearchMasterById searchMasterId(IsearchMasterById isearchMasterById);

    public OsearchMaster searchMasterByHolder(IsearchMasterByHolder isearchMasterByHolder);

    public OeditMaster editMaster(IeditMaster ieditMaster, String userName);

    public OdeleteMaster deleteMaster(IdeleteMaster ideleteMaster, String userName);
}
