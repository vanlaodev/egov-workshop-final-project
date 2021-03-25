package inventory.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class InventoryDetail implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private Integer inventoryMasterId;

    @Column(nullable = false)
    private Integer assetId;

    @Column
    private Integer budgetYear;

    @Column
    private Integer assetType;

    @Column
    private Float value;

    @Column
    private Integer year;

    @Column
    private String economicClass;

    @Column(length = 1024)
    private String description;

    @Column(length = 1024)
    private String remarkCn;

    @Column(length = 1024)
    private String remarkPt;

    @Column(length = 1024)
    private String supplier;

    @Column
    private Integer retrievalMethod;

    @Column
    private Integer assetNum;

    @Column
    private String dimension;

    @Column
    private String brand;

    @Column
    private String serialNum;

    @Column
    private String modelNum;

    @Column
    private String feature;

    @Column
    private String location;

    @Column
    private Integer dept;

    @Column
    private String userName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    @JsonFormat(pattern="yyyy/MM/dd")
    private Date actionTime;

    @Column
    private String actionBy;

    @Column
    private String actionResult;


    public InventoryDetail() {
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

    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }

    public Integer getBudgetYear() {
        return budgetYear;
    }

    public void setBudgetYear(Integer budgetYear) {
        this.budgetYear = budgetYear;
    }

    public Integer getAssetType() {
        return assetType;
    }

    public void setAssetType(Integer assetType) {
        this.assetType = assetType;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getEconomicClass() {
        return economicClass;
    }

    public void setEconomicClass(String economicClass) {
        this.economicClass = economicClass;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemarkCn() {
        return remarkCn;
    }

    public void setRemarkCn(String remarkCn) {
        this.remarkCn = remarkCn;
    }

    public String getRemarkPt() {
        return remarkPt;
    }

    public void setRemarkPt(String remarkPt) {
        this.remarkPt = remarkPt;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Integer getRetrievalMethod() {
        return retrievalMethod;
    }

    public void setRetrievalMethod(Integer retrievalMethod) {
        this.retrievalMethod = retrievalMethod;
    }

    public Integer getAssetNum() {
        return assetNum;
    }

    public void setAssetNum(Integer assetNum) {
        this.assetNum = assetNum;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getModelNum() {
        return modelNum;
    }

    public void setModelNum(String modelNum) {
        this.modelNum = modelNum;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getDept() {
        return dept;
    }

    public void setDept(Integer dept) {
        this.dept = dept;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    public String getActionBy() {
        return actionBy;
    }

    public void setActionBy(String actionBy) {
        this.actionBy = actionBy;
    }

    public String getActionResult() {
        return actionResult;
    }

    public void setActionResult(String actionResult) {
        this.actionResult = actionResult;
    }

    public Integer getInventoryMasterId() {
        return inventoryMasterId;
    }

    public void setInventoryMasterId(Integer inventoryMasterId) {
        this.inventoryMasterId = inventoryMasterId;
    }
}
