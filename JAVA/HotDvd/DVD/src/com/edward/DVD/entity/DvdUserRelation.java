package com.edward.DVD.entity;

public class DvdUserRelation {
    private String id;
    private String DvdId;
    private String UserId;
    private String operateDate;

    public DvdUserRelation() {
    }

    public DvdUserRelation(String id, String dvdId, String userId, String operateDate) {
        this.id = id;
        DvdId = dvdId;
        UserId = userId;
        this.operateDate = operateDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDvdId() {
        return DvdId;
    }

    public void setDvdId(String dvdId) {
        DvdId = dvdId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(String operateDate) {
        this.operateDate = operateDate;
    }
}
