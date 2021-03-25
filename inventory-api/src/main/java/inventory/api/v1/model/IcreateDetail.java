package inventory.api.v1.model;

import java.io.Serializable;

public class IcreateDetail extends IBaseClass implements Serializable {

    private Integer inventoryMasterId;
    private Integer assetId;
    private Integer assetNum;
    private Integer assetType;
    private String brand;
    private Integer budgetYear;
    private Integer dept;
    private String description;
    private String dimension;
    private String economicClass;
    private String feature;
    private String location;
    private String modelNum;
    private String remarkCn;
    private String remarkPt;
    private Integer retrievalMethod;
    private String serialNum;
    private String supplier;
    private String userName;
    private float value;
    private Integer year;

    public IcreateDetail(){}


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


    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
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

    public Integer getDept() {
        return dept;
    }

    public void setDept(Integer dept) {
        this.dept = dept;
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

    public Integer getInventoryMasterId() {
        return inventoryMasterId;
    }

    public void setInventoryMasterId(Integer inventoryMasterId) {
        this.inventoryMasterId = inventoryMasterId;
    }
}