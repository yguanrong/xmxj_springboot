package com.example.springboot1.model;

public class CountForUser {
    private String userName;
    private int count;
    private int dangerState;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getDangerState() {
        return dangerState;
    }

    public void setDangerState(int dangerState) {
        this.dangerState = dangerState;
    }
}
