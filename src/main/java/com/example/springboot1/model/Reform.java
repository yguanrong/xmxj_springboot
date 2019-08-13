package com.example.springboot1.model;

import java.sql.Date;

public class Reform {

    private Integer id;
    private Integer dangerId;
    private Integer zhenggaiPId;
    private String reformLog;
    private Date reformDate;
    private String dangerData;

    public Date getReformDate() {
        return reformDate;
    }

    public void setReformDate(Date reformDate) {
        this.reformDate = reformDate;
    }

    public String getDangerData() {
        return dangerData;
    }

    public void setDangerData(String dangerData) {
        this.dangerData = dangerData;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDangerId() {
        return dangerId;
    }

    public void setDangerId(Integer dangerId) {
        this.dangerId = dangerId;
    }

    public Integer getZhenggaiPId() {
        return zhenggaiPId;
    }

    public void setZhenggaiPId(Integer zhenggaiPId) {
        this.zhenggaiPId = zhenggaiPId;
    }

    public String getReformLog() {
        return reformLog;
    }

    public void setReformLog(String reformLog) {
        this.reformLog = reformLog;
    }
}
