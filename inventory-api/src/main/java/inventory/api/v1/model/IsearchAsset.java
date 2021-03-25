package inventory.api.v1.model;

import java.io.Serializable;

public class IsearchAsset extends IBaseClass implements Serializable {

    private Integer deptId;

    public IsearchAsset(){}


    /***************************************
     *
     * private function
     *
     ***************************************/


    /***************************************
     *
     * Auto Gen
     *
     ***************************************/
    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

}