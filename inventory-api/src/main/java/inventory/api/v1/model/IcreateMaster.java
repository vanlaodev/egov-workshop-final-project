package inventory.api.v1.model;

import inventory.utils.StringUtils;

import java.io.Serializable;

public class IcreateMaster extends IBaseClass implements Serializable {

    private Integer deptId;
    private String fromTime;
    private String endTime;
    private String title;
    private String remark;
    private String userName;

    public IcreateMaster(){}


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

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}