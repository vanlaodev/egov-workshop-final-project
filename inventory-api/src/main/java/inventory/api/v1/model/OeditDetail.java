package inventory.api.v1.model;

import inventory.entity.InventoryDetail;
import inventory.utils.StringUtils;

import java.io.Serializable;

public class OeditDetail extends ObaseClass implements Serializable {

    private InventoryDetail data;

    public static OeditDetail returnError(String msg){
        OeditDetail editDetail = new OeditDetail();
        editDetail.setCode(-1);
        if(!StringUtils.isEmpty(msg))
            editDetail.setMsg(msg);
        return editDetail;
    }

    public static OeditDetail returnSuccess(InventoryDetail inventoryDetail, String msg){
        OeditDetail editDetail = returnSuccess(msg);
        editDetail.setData(inventoryDetail);
        return editDetail;
    }

    public static OeditDetail returnSuccess(String msg){
        OeditDetail editDetail = new OeditDetail();
        editDetail.setCode(0);
        if(!StringUtils.isEmpty(msg))
            editDetail.setMsg(msg);
        return editDetail;
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
