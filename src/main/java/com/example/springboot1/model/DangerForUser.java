package com.example.springboot1.model;

import java.util.List;

public class DangerForUser {
    private List<String> dangerUser;
    private int[] overdueData;
    private int[] sumData;

    public List<String> getDangerUser() {
        return dangerUser;
    }

    public void setDangerUser(List<String> dangerUser) {
        this.dangerUser = dangerUser;
    }

    public int[] getOverdueData() {
        return overdueData;
    }

    public void setOverdueData(int[] overdueData) {
        this.overdueData = overdueData;
    }

    public int[] getSumData() {
        return sumData;
    }

    public void setSumData(int[] sumData) {
        this.sumData = sumData;
    }
}
