package com.edward.DVD.dao;

import com.edward.DVD.entity.DvdUserRelation;

import java.text.SimpleDateFormat;

public class DvdUserRelationManager {
    static DvdUserRelation[] drs = new DvdUserRelation[36500];
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    /**
     * 增加
     * @param userId
     * @param dvdId
     * @return
     */
    DvdUserRelation add(String userId,String dvdId){
        DvdUserRelation dr = new DvdUserRelation();

        return dr;
    }




}
