package inventory.api.v1.model;

import inventory.entity.InventoryMaster;
import inventory.utils.StringUtils;

import java.io.Serializable;

public class OeditMaster extends ObaseClass implements Serializable {

    private InventoryMaster data;

    public static OeditMaster returnError(String msg){
        OeditMaster createMaster = new OeditMaster();
        createMaster.setCode(-1);
        if(!StringUtils.isEmpty(msg))
            createMaster.setMsg(msg);
        return createMaster;
    }

    public static OeditMaster returnError(String msg, Integer code){
        OeditMaster createMaster = new OeditMaster();
        createMaster.setCode(code);
        if(!StringUtils.isEmpty(msg))
            createMaster.setMsg(msg);
        return createMaster;
    }

    public static OeditMaster returnSuccess(InventoryMaster inventoryMaster, String msg){
        OeditMaster createMaster = returnSuccess(msg);
        createMaster.setData(inventoryMaster);
        return createMaster;
    }

    public static OeditMaster returnSuccess(String msg){
        OeditMaster createMaster = new OeditMaster();
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
