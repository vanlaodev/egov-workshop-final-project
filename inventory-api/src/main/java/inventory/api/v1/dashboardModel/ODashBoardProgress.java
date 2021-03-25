package inventory.api.v1.dashboardModel;

import java.io.Serializable;
import java.util.List;

public class ODashBoardProgress implements Serializable {

    private Integer code;
    private String msg;
    private Data data;

    /***************************************
     *
     * Inner Class
     *
     ***************************************/
    public static class Data{
        private Integer year;
        private String deptName;
        private List<Progress> progress;

        public Data() {super();}


        public Integer getYear() {
            return year;
        }

        public void setYear(Integer year) {
            this.year = year;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public List<Progress> getProgress() {
            return progress;
        }

        public void setProgress(List<Progress> progress) {
            this.progress = progress;
        }
    }


    /***************************************
     *
     * Auto Gen
     *
     ***************************************/
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
