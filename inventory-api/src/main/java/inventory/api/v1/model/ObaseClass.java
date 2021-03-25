package inventory.api.v1.model;

import inventory.utils.StringUtils;

import java.io.Serializable;

public class ObaseClass implements Serializable {
    private Integer code; //Success=0; failed=-1
    private String msg;

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
}

