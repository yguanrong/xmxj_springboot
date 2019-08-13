package com.example.springboot1.model;

import java.sql.Timestamp;

/**
 * Created by GuanRong on 2019/4/7.
 */

public class Zhenggai {
    private Integer id;
    private Integer danger_id;
    private String danger_name;
    private String zhenggai_dec;
    private String zhenggai_img;
    private Integer zhenggai_P_id;
    private Timestamp zhenggai_time;
    private Integer review_p_id;
    private Integer itemId;
    private String review_log;
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDanger_id() {
        return danger_id;
    }

    public void setDanger_id(Integer danger_id) {
        this.danger_id = danger_id;
    }

    public String getDanger_name() {
        return danger_name;
    }

    public void setDanger_name(String danger_name) {
        this.danger_name = danger_name;
    }

    public String getZhenggai_dec() {
        return zhenggai_dec;
    }

    public void setZhenggai_dec(String zhenggai_dec) {
        this.zhenggai_dec = zhenggai_dec;
    }

    public String getZhenggai_img() {
        return zhenggai_img;
    }

    public void setZhenggai_img(String zhenggai_img) {
        this.zhenggai_img = zhenggai_img;
    }

    public Integer getZhenggai_P_id() {
        return zhenggai_P_id;
    }

    public void setZhenggai_P_id(Integer zhenggai_P_id) {
        this.zhenggai_P_id = zhenggai_P_id;
    }

    public Timestamp getZhenggai_time() {
        return zhenggai_time;
    }

    public void setZhenggai_time(Timestamp zhenggai_time) {
        this.zhenggai_time = zhenggai_time;
    }

    public Integer getReview_p_id() {
        return review_p_id;
    }

    public void setReview_p_id(Integer review_p_id) {
        this.review_p_id = review_p_id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getReview_log() {
        return review_log;
    }

    public void setReview_log(String review_log) {
        this.review_log = review_log;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
