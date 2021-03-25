package inventory.api.v1.model;

import inventory.entity.Asset;
import inventory.utils.StringUtils;

import java.io.Serializable;
import java.util.List;

public class OsearchAsset extends ObaseClass implements Serializable {

    private List<Asset> data;

    public static OsearchAsset returnError(String msg){
        OsearchAsset searchAsset = new OsearchAsset();
        searchAsset.setCode(-1);
        if(!StringUtils.isEmpty(msg))
            searchAsset.setMsg(msg);
        return searchAsset;
    }

    public static OsearchAsset returnSuccess(List<Asset> assets, String msg){
        OsearchAsset searchAsset = returnSuccess(msg);
        searchAsset.setData(assets);
        return searchAsset;
    }

    public static OsearchAsset returnSuccess(String msg){
        OsearchAsset searchAsset = new OsearchAsset();
        searchAsset.setCode(0);
        if(!StringUtils.isEmpty(msg))
            searchAsset.setMsg(msg);
        return searchAsset;
    }


    /***************************************
     *
     * Auto Gen
     *
     ***************************************/
    public List<Asset> getData() {
        return data;
    }

    public void setData(List<Asset> data) {
        this.data = data;
    }
}
