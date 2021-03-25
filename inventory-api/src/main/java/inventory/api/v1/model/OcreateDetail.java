package inventory.api.v1.model;

import inventory.entity.InventoryDetail;
import inventory.utils.StringUtils;

import java.io.Serializable;

public class OcreateDetail extends ObaseClass implements Serializable {

    private InventoryDetail data;

    public static OcreateDetail returnError(String msg){
        OcreateDetail ocreateDetail = new OcreateDetail();
        ocreateDetail.setCode(-1);
        if(!StringUtils.isEmpty(msg))
            ocreateDetail.setMsg(msg);
        return ocreateDetail;
    }

    public static OcreateDetail returnSuccess(InventoryDetail inventoryDetail, String msg){
        OcreateDetail ocreateDetail = returnSuccess(msg);
        ocreateDetail.setData(inventoryDetail);
        return ocreateDetail;
    }

    public static OcreateDetail returnSuccess(String msg){
        OcreateDetail ocreateDetail = new OcreateDetail();
        ocreateDetail.setCode(0);
        if(!StringUtils.isEmpty(msg))
            ocreateDetail.setMsg(msg);
        return ocreateDetail;
    }


    /***************************************
     *
     * Auto Gen
     *
     ***************************************/
    public InventoryDetail getData() {
        return data;
    }

    public void setData(InventoryDetail data) {
        this.data = data;
    }
}
