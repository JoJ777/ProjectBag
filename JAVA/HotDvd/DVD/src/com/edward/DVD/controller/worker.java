package com.edward.DVD.controller;

import com.edward.DVD.dao.DvdManager;
import com.edward.DVD.entity.Dvd;
import com.edward.DVD.util.Tools;

import java.util.Scanner;

public class worker {
    DvdManager dm = new DvdManager();

    /**
     * 主菜单
     */
    public void menu(){
        while(true){
            System.out.println(" 欢迎来到DVD出租管理信息系统 ");
            System.out.println("\t\t1、展示DVD：");
            System.out.println("\t\t2、上架DVD：");
            System.out.println("\t\t3、下架DVD：");
            System.out.println("\t\t4、出租DVD：");
            System.out.println("\t\t5、归还DVD：");
            System.out.println("\t\t6、查找DVD：");
            System.out.println("\t\t7、排序DVD：");
            System.out.println("\t\t0、退出");
            System.out.println("_________________________________");
            System.out.println("请输入:");
            Scanner sc = new Scanner(System.in);
            switch (sc.nextInt()){
                case 1:
                    //展示DVD
                    System.out.println("展示功能！");
                    System.out.println("DVD：");
                    dm.printDvdArray(dm.dvds);
                    break;
                case 2:
                    //上架
                    System.out.println("上架功能！");
                    if(dm.upLine()){
                        //成功
                        System.out.println("上架成功！");
                    }
                    else{
                        System.out.println("上架失败！！！");
                    }
                    break;
                case 3:
                    //下架
                    sc = new Scanner(System.in);
                    System.out.println("下架功能！");
                    System.out.println("请输入要下架的DVD的名称或者ID：");
                    String input = sc.nextLine();
                    Dvd dvd = dm.searchByNameOrById(input);
                    if(dm.downLine(dvd)){
                        System.out.println("下架成功~~~");
                    }else{
                        System.out.println("下架失败~~~");
                    }
                    break;
                case 4:
                    //出租
                    if (dm.borrowDvd()) {
                        System.out.println("出租成功~~~");
                    }else{
                        System.out.println("出租失败~~~");
                    }
                    break;
                case 5:
                    //归还
                    if (dm.returnDvd()) {
                        System.out.println("归还成功！欢迎下次再来~~~");
                    }else{
                        System.out.println("归还失败~~~");
                    }
                    break;
                case 6:
                    //查找：name模糊查找
                    sc = new Scanner(System.in);
                    System.out.println("请输入要查找的DVD名称关键词：");
                    String keyWords = sc.nextLine();
                    Dvd[] dvdArray = dm.searchByKeyWords(keyWords);
                    int dvdArraySize = Tools.getFreePosition(dvdArray);
                    if(dvdArraySize!=0){
                        System.out.println("找到相关DVD：");
                        dm.printDvdArray(dvdArray);
                    }else {
                        System.out.println("本店没有包含该关键字：“"+keyWords+"”的DVD~~~");
                    }
                    break;
                case 7:
                    //排序
                    dm.sortDVD();
                    break;
                case 0:
                    //退出
                    System.out.println("您选择了退出！");
                    return;
                default:
                    System.out.println("您输入的指令有误！");
            }
        }
    }
}
