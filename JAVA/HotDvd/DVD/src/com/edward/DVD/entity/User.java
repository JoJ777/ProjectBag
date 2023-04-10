package com.edward.DVD.entity;

import com.edward.DVD.enums.UserStatus;

public class User {
    private String id;
    private String name;
    private String mobile;
    private double money;
    private UserStatus status;//用户状态

    public User() {
    }

    public User(String id, String name, String mobile) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.status = UserStatus.SLEEP;
    }

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", money=" + money +
                ", status=" + status +
                '}';
    }
}
