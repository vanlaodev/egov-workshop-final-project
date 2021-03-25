package inventory.api.v1.model;

import inventory.utils.StringUtils;

import java.io.Serializable;

public class OdeleteDetail extends ObaseClass implements Serializable {

    public static OdeleteDetail returnError(String msg) {
        OdeleteDetail deleteDetail = new OdeleteDetail();
        deleteDetail.setCode(-1);
        if (!StringUtils.isEmpty(msg))
            deleteDetail.setMsg(msg);
        return deleteDetail;
    }

    public static OdeleteDetail returnSuccess(String msg) {
        OdeleteDetail deleteDetail = new OdeleteDetail();
        deleteDetail.setCode(0);
        if (!StringUtils.isEmpty(msg))
            deleteDetail.setMsg(msg);
        return deleteDetail;
    }


    /***************************************
     *
     * Auto Gen
     *
     ***************************************/

}