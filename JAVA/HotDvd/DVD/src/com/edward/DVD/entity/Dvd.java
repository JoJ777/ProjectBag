package com.edward.DVD.entity;

import com.edward.DVD.enums.DvdStatus;

public class Dvd {
    private String id;//id
    private String name;//片名
    private double cost;//租金
    private double profit;//押金
    private int times;//出租次数
    private String borrowDate;//出租日期
    private String returnDate;//归还
    private DvdStatus status;//状态
    private int index;//索引

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public DvdStatus getStatus() {
        return status;
    }

    public void setStatus(DvdStatus status) {
        this.status = status;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    @Override
    public String toString() {
        return "Dvd{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", profit=" + profit +
                ", times=" + times +
                ", borrowDate='" + borrowDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", status=" + status +
                ", index=" + index +
                '}';
    }
}
