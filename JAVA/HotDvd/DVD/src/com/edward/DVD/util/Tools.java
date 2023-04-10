package com.edward.DVD.util;

public class Tools {
    //获取空位置的方法
    public static int getFreePosition(Object... args){
        int index = -1;
        for (int i = 0; i < args.length; i++) {
            if(args[i]==null){
                index = i;
                break;
            }
        }
        return index;
    }
}
