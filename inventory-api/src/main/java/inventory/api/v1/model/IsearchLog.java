package inventory.api.v1.model;

public class IsearchLog extends IBaseClass{

    private Integer masterId;
    private String masterTitle;
    private String startDate;
    private String endDate;


    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getMasterTitle() {
        return masterTitle;
    }

    public void setMasterTitle(String masterTitle) {
        this.masterTitle = masterTitle;
    }
}
