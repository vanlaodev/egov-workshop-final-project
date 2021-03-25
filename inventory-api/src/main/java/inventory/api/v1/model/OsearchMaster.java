package inventory.api.v1.model;

import inventory.entity.InventoryMaster;
import inventory.utils.StringUtils;

import java.io.Serializable;
import java.util.List;

public class OsearchMaster extends ObaseClass implements Serializable {

    private List<InventoryMaster> data;

    public static OsearchMaster returnError(String msg){
        OsearchMaster createMaster = new OsearchMaster();
        createMaster.setCode(-1);
        if(!StringUtils.isEmpty(msg))
            createMaster.setMsg(msg);
        return createMaster;
    }

    public static OsearchMaster returnError(String msg, Integer code){
        OsearchMaster createMaster = new OsearchMaster();
        createMaster.setCode(-1);
        if(!StringUtils.isEmpty(msg))
            createMaster.setMsg(msg);
        return createMaster;
    }

    public static OsearchMaster returnSuccess(List<InventoryMaster> inventoryMasters, String msg){
        OsearchMaster createMaster = returnSuccess(msg);
        createMaster.setData(inventoryMasters);
        return createMaster;
    }

    public static OsearchMaster returnSuccess(String msg){
        OsearchMaster createMaster = new OsearchMaster();
        createMaster.setCode(0);
        if(!StringUtils.isEmpty(msg))
            createMaster.setMsg(msg);
        return createMaster;
    }


    /***************************************
     *
     * Auto Gen
     *
     ***************************************/
    public List<InventoryMaster> getData() {
        return data;
    }

    public void setData(List<InventoryMaster> data) {
        this.data = data;
    }
}
