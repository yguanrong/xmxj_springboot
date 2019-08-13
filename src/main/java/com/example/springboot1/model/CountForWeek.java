package com.example.springboot1.model;


import java.util.List;

public class CountForWeek {
    private List<String> dates ;
    private int[] importantDanger;//紧要
    private int[] seriousDanger ;//严重
    private int[] commonDanger ;//一般
    private int[] count  ;//总数

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }

    public int[] getImportantDanger() {
        return importantDanger;
    }

    public void setImportantDanger(int[] importantDanger) {
        this.importantDanger = importantDanger;
    }

    public int[] getSeriousDanger() {
        return seriousDanger;
    }

    public void setSeriousDanger(int[] seriousDanger) {
        this.seriousDanger = seriousDanger;
    }

    public int[] getCommonDanger() {
        return commonDanger;
    }

    public void setCommonDanger(int[] commonDanger) {
        this.commonDanger = commonDanger;
    }

    public int[] getCount() {
        return count;
    }

    public void setCount(int[] count) {
        this.count = count;
    }
}
