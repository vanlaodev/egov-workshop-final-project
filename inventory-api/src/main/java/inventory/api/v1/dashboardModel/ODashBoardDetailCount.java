package inventory.api.v1.dashboardModel;

import java.io.Serializable;
import java.util.List;

public class ODashBoardDetailCount implements Serializable {

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
        private List<DetailCount> detailCount;

        public Data() {super();}

        public Integer getYear() {
            return year;
        }

        public void setYear(Integer year) {
            this.year = year;
        }
        public List<DetailCount> getDetailCount() {
            return detailCount;
        }
        public void setDetailCount(List<DetailCount> detailCount) {
            this.detailCount = detailCount;
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
