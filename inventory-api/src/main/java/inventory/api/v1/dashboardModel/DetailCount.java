package inventory.api.v1.dashboardModel;

import java.io.Serializable;

public class DetailCount implements Serializable {
    private String masterTitle;
    private Integer detailCount;

    public DetailCount(String masterTitle, Integer detailCount) {
        this.masterTitle = masterTitle;
        this.detailCount = detailCount;
    }

    public String getMasterTitle() {
        return masterTitle;
    }

    public void setMasterTitle(String masterTitle) {
        this.masterTitle = masterTitle;
    }

    public Integer getDetailCount() {
        return detailCount;
    }

    public void setDetailCount(Integer detailCount) {
        this.detailCount = detailCount;
    }
}
