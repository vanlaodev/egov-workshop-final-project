package inventory.api.v1.model;

import inventory.entity.InventoryDetail;
import inventory.utils.StringUtils;

import java.io.Serializable;
import java.util.List;

public class OsearchDetail extends ObaseClass implements Serializable {

    private List<InventoryDetail> data;

    public static OsearchDetail returnError(String msg){
        OsearchDetail searchDetail = new OsearchDetail();
        searchDetail.setCode(-1);
        if(!StringUtils.isEmpty(msg))
            searchDetail.setMsg(msg);
        return searchDetail;
    }

    public static OsearchDetail returnSuccess(List<InventoryDetail> inventoryDetails, String msg){
        OsearchDetail searchDetail = returnSuccess(msg);
        searchDetail.setData(inventoryDetails);
        return searchDetail;
    }

    public static OsearchDetail returnSuccess(String msg){
        OsearchDetail searchDetail = new OsearchDetail();
        searchDetail.setCode(0);
        if(!StringUtils.isEmpty(msg))
            searchDetail.setMsg(msg);
        return searchDetail;
    }


    /***************************************
     *
     * Auto Gen
     *
     ***************************************/
    public List<InventoryDetail> getData() {
        return data;
    }

    public void setData(List<InventoryDetail> data) {
        this.data = data;
    }
}
