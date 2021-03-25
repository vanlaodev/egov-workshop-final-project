package inventory.api.v1.model;

import inventory.entity.InventoryMaster;
import inventory.utils.StringUtils;

import java.io.Serializable;
import java.util.List;

public class OgetPermission extends ObaseClass implements Serializable {

    private Permission data;

    public static OgetPermission returnError(String msg){
        OgetPermission getPermission = new OgetPermission();
        getPermission.setCode(-1);
        if(!StringUtils.isEmpty(msg))
            getPermission.setMsg(msg);
        return getPermission;
    }

    public static OgetPermission returnError(String msg, Integer code){
        OgetPermission getPermission = new OgetPermission();
        getPermission.setCode(code);
        if(!StringUtils.isEmpty(msg))
            getPermission.setMsg(msg);
        return getPermission;
    }

    public static OgetPermission returnSuccess(Permission data, String msg){
        OgetPermission getPermission = returnSuccess(msg);
        getPermission.setData(data);
        return getPermission;
    }

    public static OgetPermission returnSuccess(String msg){
        OgetPermission getPermission = new OgetPermission();
        getPermission.setCode(0);
        if(!StringUtils.isEmpty(msg))
            getPermission.setMsg(msg);
        return getPermission;
    }

    public Permission getData() {
        return data;
    }

    public void setData(Permission data) {
        this.data = data;
    }



    /***************************************
     *
     * Auto Gen
     *
     ***************************************/

}
