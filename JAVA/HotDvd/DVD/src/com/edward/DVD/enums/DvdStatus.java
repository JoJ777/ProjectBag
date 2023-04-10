package com.edward.DVD.enums;

/**
 * 定义一个枚举类型
 */
public enum DvdStatus {
    //定义一系列的枚举常量
    INSTORE(0,"在店"),
    OUTSTORE(1,"出租"),
    PREORDER(2,"预订"),
    BADDISK(3,"坏碟")
    ;
    int code;//状态码
    String message;//状态描述

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    DvdStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
