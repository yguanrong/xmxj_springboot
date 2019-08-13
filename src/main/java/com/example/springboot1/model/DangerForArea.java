package com.example.springboot1.model;

import java.util.List;

public class DangerForArea {
    private List<String> dangerArea;
    private int[] importantArea;
    private int[] seriousArea;
    private int[] countArea;

    public List<String> getDangerArea() {
        return dangerArea;
    }

    public void setDangerArea(List<String> dangerArea) {
        this.dangerArea = dangerArea;
    }

    public int[] getImportantArea() {
        return importantArea;
    }

    public void setImportantArea(int[] importantArea) {
        this.importantArea = importantArea;
    }

    public int[] getSeriousArea() {
        return seriousArea;
    }

    public void setSeriousArea(int[] seriousArea) {
        this.seriousArea = seriousArea;
    }

    public int[] getCountArea() {
        return countArea;
    }

    public void setCountArea(int[] countArea) {
        this.countArea = countArea;
    }
}
