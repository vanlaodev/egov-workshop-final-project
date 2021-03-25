package inventory.api.v1.model;

import inventory.entity.Log;
import inventory.utils.StringUtils;

import java.util.List;

public class OsearchLog extends ObaseClass{

    private List<Log> data;

    public static OsearchLog returnError(String msg){
        OsearchLog ret = new OsearchLog();
        ret.setCode(-1);
        if(!StringUtils.isEmpty(msg))
            ret.setMsg(msg);
        return ret;
    }

    public static OsearchLog returnError(String msg, Integer code){
        OsearchLog ret = new OsearchLog();
        ret.setCode(code);
        if(!StringUtils.isEmpty(msg))
            ret.setMsg(msg);
        return ret;
    }

    public static OsearchLog returnSuccess(List<Log> logs, String msg){
        OsearchLog ret = returnSuccess(msg);
        ret.setData(logs);
        return ret;
    }

    public static OsearchLog returnSuccess(String msg){
        OsearchLog ret = new OsearchLog();
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
    public List<Log> getData() {
        return data;
    }

    public void setData(List<Log> data) {
        this.data = data;
    }
}
