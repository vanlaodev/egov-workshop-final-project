package inventory.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column
    private String nameCn;

    @Column
    private String namePt;


    public Department() {
    }


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

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getNamePt() {
        return namePt;
    }

    public void setNamePt(String namePt) {
        this.namePt = namePt;
    }
}
