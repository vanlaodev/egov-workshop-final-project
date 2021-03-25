package inventory.api.v1.model;

import java.io.Serializable;

public class IdeleteMaster extends IBaseClass implements Serializable {

    private Integer id;

    public IdeleteMaster(){}


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
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}