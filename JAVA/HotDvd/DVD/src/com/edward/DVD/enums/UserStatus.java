package com.edward.DVD.enums;

public enum UserStatus {
    //定义一系列的枚举常量
    SLEEP(0,"睡眠"),
    ACTIVE(1,"活动")
    ;
    int code;
    String message;

    UserStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
