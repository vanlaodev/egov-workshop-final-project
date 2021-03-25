package inventory.api.v1.dashboardModel;

import java.io.Serializable;

public class Counter implements Serializable {
    private String deptName;
    private Integer count;

    public Counter(String deptName, Integer count) {
        this.deptName = deptName;
        this.count = count;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
