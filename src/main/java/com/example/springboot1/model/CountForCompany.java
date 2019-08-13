package com.example.springboot1.model;

import java.util.List;

public class CountForCompany {

    private List<String> dangerCompany;
    private int[] overdueData;
    private int[] sumData;

    public List<String> getDangerCompany() {
        return dangerCompany;
    }

    public void setDangerCompany(List<String> dangerCompany) {
        this.dangerCompany = dangerCompany;
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
