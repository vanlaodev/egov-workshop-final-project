package inventory.api.v1.model;

import inventory.entity.InventoryMaster;
import inventory.utils.StringUtils;

import java.io.Serializable;

public class OcreateMaster extends ObaseClass implements Serializable {

    private InventoryMaster data;

    public static OcreateMaster returnError(String msg){
        OcreateMaster createMaster = new OcreateMaster();
        createMaster.setCode(-1);
        if(!StringUtils.isEmpty(msg))
            createMaster.setMsg(msg);
        return createMaster;
    }

    public static OcreateMaster returnError(String msg, Integer code){
        OcreateMaster createMaster = new OcreateMaster();
        createMaster.setCode(code);
        if(!StringUtils.isEmpty(msg))
            createMaster.setMsg(msg);
        return createMaster;
    }

    public static OcreateMaster returnSuccess(InventoryMaster inventoryMaster, String msg){
        OcreateMaster createMaster = returnSuccess(msg);
        createMaster.setData(inventoryMaster);
        return createMaster;
    }

    public static OcreateMaster returnSuccess(String msg){
        OcreateMaster createMaster = new OcreateMaster();
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
    public InventoryMaster getData() {
        return data;
    }

    public void setData(InventoryMaster data) {
        this.data = data;
    }
}
