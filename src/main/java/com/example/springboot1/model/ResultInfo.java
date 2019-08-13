package com.example.springboot1.model;

public class ResultInfo {

    private Integer state;
    private String msg;


    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultInfo() {
    }

    public ResultInfo(Integer state, String data) {
        this.state = state;
        this.msg = data;
    }
}
