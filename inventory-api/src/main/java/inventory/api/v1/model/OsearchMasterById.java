package inventory.api.v1.model;

import inventory.entity.InventoryMaster;
import inventory.utils.StringUtils;
import java.io.Serializable;

public class OsearchMasterById extends ObaseClass implements Serializable {

    private InventoryMaster data;

    public static OsearchMasterById returnError(String msg){
        OsearchMasterById ret = new OsearchMasterById();
        ret.setCode(-1);
        if(!StringUtils.isEmpty(msg))
            ret.setMsg(msg);
        return ret;
    }

    public static OsearchMasterById returnError(String msg, Integer code){
        OsearchMasterById ret = new OsearchMasterById();
        ret.setCode(-1);
        if(!StringUtils.isEmpty(msg))
            ret.setMsg(msg);
        return ret;
    }

    public static OsearchMasterById returnSuccess(InventoryMaster inventoryMaster, String msg){
        OsearchMasterById ret = returnSuccess(msg);
        ret.setData(inventoryMaster);
        return ret;
    }

    public static OsearchMasterById returnSuccess(String msg){
        OsearchMasterById ret = new OsearchMasterById();
        ret.setCode(0);
        if(!StringUtils.isEmpty(msg))
            ret.setMsg(msg);
        return ret;
    }

    /***************************************
     *
     * Auto Gen
     *
     ***************************************/
    public InventoryMaster getData() {
        return data;
    }

    public void setData(InventoryMaster data) {
        this.data = data;
    }
}
