package inventory.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Asset implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column
    private Integer assetNum;

    @Column
    private Integer assetType;

    @Column
    private String brand;

    @Column
    private Integer budgetYear;

    @Column
    private Integer deptId;

    @Column
    private String description;

    @Column
    private String dimension;

    @Column
    private String economicClass;

    @Column
    private String feature;

    @Column
    private String location;

    @Column
    private String modelNum;

    @Column
    private String remarkCn;

    @Column
    private String remarkPt;

    @Column
    private Integer retrievalMethod;

    @Column
    private String serialNum;

    @Column
    private String supplier;

    @Column
    private String userName;

    @Column
    private float value;

    @Column
    private Integer year;

    public Asset() {
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

    public Integer getAssetNum() {
        return assetNum;
    }

    public void setAssetNum(Integer assetNum) {
        this.assetNum = assetNum;
    }

    public Integer getAssetType() {
        return assetType;
    }

    public void setAssetType(Integer assetType) {
        this.assetType = assetType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getBudgetYear() {
        return budgetYear;
    }

    public void setBudgetYear(Integer budgetYear) {
        this.budgetYear = budgetYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getEconomicClass() {
        return economicClass;
    }

    public void setEconomicClass(String economicClass) {
        this.economicClass = economicClass;
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

    public String getModelNum() {
        return modelNum;
    }

    public void setModelNum(String modelNum) {
        this.modelNum = modelNum;
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

    public Integer getRetrievalMethod() {
        return retrievalMethod;
    }

    public void setRetrievalMethod(Integer retrievalMethod) {
        this.retrievalMethod = retrievalMethod;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
}
