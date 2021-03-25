package inventory.api.v1.model;

import java.io.Serializable;

public class IsearchDetail extends IBaseClass implements Serializable {

    private Integer masterId;
    private String userName;

    public IsearchDetail(){}


    /***************************************
     *
     * private function
     *
     ***************************************/


    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    /***************************************
     *
     * Auto Gen
     *
     ***************************************/


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}