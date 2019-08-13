package com.example.springboot1.model;

/**
 * Created by GuanRong on 2019/3/25.
 */

public class Item {

    private Integer id;
    private String itemName;
    private String companyId;
    private Integer manageId;
    private String saferOfficeId;
    private String responserId;
    private Integer state;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Integer getManageId() {
        return manageId;
    }

    public void setManageId(Integer manageId) {
        this.manageId = manageId;
    }

    public String getSaferOfficeId() {
        return saferOfficeId;
    }

    public void setSaferOfficeId(String saferOfficeId) {
        this.saferOfficeId = saferOfficeId;
    }

    public String getResponserId() {
        return responserId;
    }

    public void setResponserId(String responserId) {
        this.responserId = responserId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

}
