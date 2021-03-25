package inventory.api.v1.dashboardModel;

import java.io.Serializable;

public class Progress implements Serializable {
    private String masterTitle;
    private Integer finishedCount;
    private Integer nonFinishCount;

    public Progress(String masterTitle, Integer finishedCount, Integer nonFinishCount) {
        this.masterTitle = masterTitle;
        this.finishedCount = finishedCount;
        this.nonFinishCount = nonFinishCount;
    }

    public String getMasterTitle() {
        return masterTitle;
    }

    public void setMasterTitle(String masterTitle) {
        this.masterTitle = masterTitle;
    }

    public Integer getFinishedCount() {
        return finishedCount;
    }

    public void setFinishedCount(Integer finishedCount) {
        this.finishedCount = finishedCount;
    }

    public Integer getNonFinishCount() {
        return nonFinishCount;
    }

    public void setNonFinishCount(Integer nonFinishCount) {
        this.nonFinishCount = nonFinishCount;
    }
}
