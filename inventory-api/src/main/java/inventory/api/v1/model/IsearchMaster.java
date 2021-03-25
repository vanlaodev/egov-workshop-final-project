package inventory.api.v1.model;

import java.io.Serializable;

public class IsearchMaster extends IBaseClass implements Serializable {

    private Integer deptId;
    private String title;

    public IsearchMaster(){}


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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}