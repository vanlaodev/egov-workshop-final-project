package inventory.api.v1.model;

import inventory.entity.InventoryMaster;
import inventory.utils.StringUtils;

import java.io.Serializable;
import java.util.List;

public class OdeleteMaster extends ObaseClass implements Serializable {

    public static OdeleteMaster returnError(String msg) {
        OdeleteMaster createMaster = new OdeleteMaster();
        createMaster.setCode(-1);
        if (!StringUtils.isEmpty(msg))
            createMaster.setMsg(msg);
        return createMaster;
    }

    public static OdeleteMaster returnError(String msg, Integer code) {
        OdeleteMaster createMaster = new OdeleteMaster();
        createMaster.setCode(code);
        if (!StringUtils.isEmpty(msg))
            createMaster.setMsg(msg);
        return createMaster;
    }

    public static OdeleteMaster returnSuccess(String msg) {
        OdeleteMaster createMaster = new OdeleteMaster();
        createMaster.setCode(0);
        if (!StringUtils.isEmpty(msg))
            createMaster.setMsg(msg);
        return createMaster;
    }


    /***************************************
     *
     * Auto Gen
     *
     ***************************************/

}