package inventory.api.v1.model;

import inventory.entity.Department;
import inventory.entity.InventoryMaster;
import inventory.utils.StringUtils;

import java.io.Serializable;
import java.util.List;

public class OfindAllDept extends ObaseClass implements Serializable {

    private List<Department> data;

    public static OfindAllDept returnError(String msg){
        OfindAllDept findAllDept = new OfindAllDept();
        findAllDept.setCode(-1);
        if(!StringUtils.isEmpty(msg))
            findAllDept.setMsg(msg);
        return findAllDept;
    }

    public static OfindAllDept returnSuccess(List<Department> departments, String msg){
        OfindAllDept findAllDept = returnSuccess(msg);
        findAllDept.setData(departments);
        return findAllDept;
    }

    public static OfindAllDept returnSuccess(String msg){
        OfindAllDept findAllDept = new OfindAllDept();
        findAllDept.setCode(0);
        if(!StringUtils.isEmpty(msg))
            findAllDept.setMsg(msg);
        return findAllDept;
    }

    /***************************************
     *
     * Auto Gen
     *
     ***************************************/
    public List<Department> getData() {
        return data;
    }

    public void setData(List<Department> data) {
        this.data = data;
    }
}
