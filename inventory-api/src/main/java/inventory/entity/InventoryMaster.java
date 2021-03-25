package inventory.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class InventoryMaster implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column
    private String remark;

    @Column(nullable = false)
    private Integer deptId;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    @JsonFormat(pattern="yyyy/MM/dd")
    private Date fromTime;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    @JsonFormat(pattern="yyyy/MM/dd")
    private Date endTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @JsonFormat(pattern="yyyy/MM/dd")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @JsonFormat(pattern="yyyy/MM/dd")
    private Date modifiedTime;

    @Column
    private String modifiedByUser;

    @Enumerated(EnumType.STRING)
    @Column
    private STATUS status;


    public InventoryMaster() {
    }

    /***************************************
     *
     * Inner Class
     *
     ***************************************/
    public enum STATUS{
        ACTIVE, INVALID;
        public static STATUS toStatus(String str){
            if(str == null)
                return null;
            if("ACTIVE".compareTo(str) ==0 )
                return STATUS.ACTIVE;
            else if("INVALID".compareTo(str) ==0 )
                return STATUS.ACTIVE;
            else
                return null;
        }
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Date getFromTime() {
        return fromTime;
    }

    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getModifiedByUser() {
        return modifiedByUser;
    }

    public void setModifiedByUser(String modifiedByUser) {
        this.modifiedByUser = modifiedByUser;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }
}
