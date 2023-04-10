package com.edward.DVD.dao;

import com.edward.DVD.entity.Dvd;
import com.edward.DVD.enums.DvdStatus;

import com.edward.DVD.util.Tools;
//import org.apache.DVD.ibatis.io.Resources;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class DvdManager {
    public static Dvd[] dvdsTemp;//dvd接收容器
    public static Dvd[] dvds = new Dvd[100];





    Scanner sc;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



    /**
     * 按名称查找/id
     * @param input
     * @return
     */
    public Dvd searchByNameOrById(String input){
        //遍历判断
        Dvd dvd = null;
        int index = Tools.getFreePosition(dvds);
        for (int i = 0; i < index; i++) {
            if(dvds[i].getName().equals(input)||dvds[i].getId().equals(input)){
                //找到
                dvd = dvds[i];
                break;
            }
        }
        return dvd;
    }


    /**
     * 打印DVD数组
     * @param dvds
     */
    public void printDvdArray(Dvd[] dvds){
        for (int i = 0; i < dvds.length; i++) {
            if(dvds[0]==null){
                System.out.println("无DVD~~~");
                break;
            }else if(dvds[i]==null){
                break;
            }
            System.out.println(dvds[i]);
        }
    }





    /**
     * 上架，更新数据库
     * @param
     * @return
     */
    public boolean upLine(){
        sc = new Scanner(System.in);
        System.out.println("请输入电影名称：");
        String dvdName = sc.nextLine();
        System.out.println("请输入日租金单价：");
        double cost = sc.nextDouble();
        //封装
        int index = Tools.getFreePosition(dvds);
        Dvd dvd = new Dvd();
        dvd.setId(UUID.randomUUID().toString().replace("-", ""));//随机产生一个32位的字符串,replace:全部替换
        dvd.setName(dvdName);
        dvd.setCost(cost);
        dvd.setStatus(DvdStatus.INSTORE);
        dvd.setTimes(0);
        dvd.setIndex(index);
        //添加到数组
        dvds[index] = dvd;

        return true;
    }

    /**
     * 下架
     * @param dvd
     * @return
     */
    public boolean downLine(Dvd dvd){
        boolean flag = false;
        //根据DVD索引，从后往前覆盖
        int index = Tools.getFreePosition(dvds);//获取最后一个位置，避免浪费资源
        for (int i = dvd.getIndex(); i < index; i++) {
            dvds[i] = dvds[i+1];
        }
        int indexLast = Tools.getFreePosition(dvds);
        if(indexLast<index){
            //如果移动后的第一个空位置的索引小于移动前的，则删除成功
            flag = true;
        }
        return flag;
    }


    /**
     * 出租，刷新数据库
     * @return
     */
    public boolean borrowDvd(){
        boolean flag = false;
        //出租哪张DVD
        //1、输入DVD名称     or  2、遍历碟片，在输入
        sc = new Scanner(System.in);
        System.out.println("请输入要租用的DVD名称或者ID：");
        String name = sc.nextLine();
        //2、判断DVD存在与否
        Dvd dvd = searchByNameOrById(name);
        if(dvd!=null){
            if(dvd.getStatus()==DvdStatus.INSTORE){
                System.out.println("DVD信息:");
                System.out.println(dvd);
                //出租时profit、times、borrowdate status改变
                System.out.println("请输入押金：");
                dvd.setProfit(sc.nextDouble());
                dvd.setTimes(dvd.getTimes()+1);
                dvd.setStatus(DvdStatus.OUTSTORE);
                dvd.setBorrowDate(sdf.format(new Date()));
                flag = true;
            }else{
                System.out.println("此DVD已出租~~~");
            }
        }else{
            System.out.println("本店无此DVD！");
        }
        return flag;
    }


    /**
     * 归还，刷新数据库
     * @return
     */
    public boolean returnDvd(){
        sc = new Scanner(System.in);
        System.out.println("请输入归还DVD的名称或ID：");
        //查dvd是否匹配、status是否一致
        Dvd dvd = searchByNameOrById(sc.nextLine());
        if(dvd!=null&&dvd.getStatus()== DvdStatus.OUTSTORE){
            //改写状态status、归还日期(改写数组中的)
            dvd.setReturnDate(sdf.format(new Date()));
            System.out.println("您于："+dvd.getReturnDate()+"归还本店名为：《"+dvd.getName()+"》的DVD~~~");
            dvd.setStatus(DvdStatus.INSTORE);
            //匹配,计算支付费用
            double price = 0;
            price -= dvd.getProfit();
            //；计算
            try {
                Date borrowDate = sdf.parse(dvd.getBorrowDate());
                Date returnDate = new Date();
                long borrowDateTime = borrowDate.getTime();
                long returnDateTime = returnDate.getTime();
                int day = (int) ((returnDateTime-borrowDateTime)/1000/60/60/24);

                if(day<1){
                    day = 1;
                }
                price+=day*dvd.getCost();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("您需支付："+price+"元~~~");
            //刷新
            try {
                System.out.println("正在刷新DVD数据......");
                Thread.sleep(1000);
                dvd.setProfit(0);
                dvd.setBorrowDate(null);
                dvd.setReturnDate(null);
                System.out.println("刷新成功：");
                System.out.println(dvd);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("无该DVD或该DVD未出租~~~");
        return false;
    }

    //=========================以上是主业务===========================

    //===========================以下是辅助方法===========================

    /**
     * 排序
     */
    public void sortDVD(){
        while(true){
            System.out.println("***** DVD出租管理信息系统 *********");
            System.out.println("\t\t1、按照热度降序排序：");
            System.out.println("\t\t2、按照单价升序排序：");
            System.out.println("\t\t0、退出");
            System.out.println("********************************");
            System.out.println("请选择:");
            sc = new Scanner(System.in);
            int command = sc.nextInt();
            switch(command){
                case 1:
                    //按照热度（times）降序
                    sortByTimes();
                    break;
                case 2:
                    //按照单价升序
                    sortByCost();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("您输入的指令有误~~~");
            }
        }
    }

    /**
     * 通过单价升序
     */
    public void sortByCost() {
        int index = Tools.getFreePosition(dvds);
        //给临时数组
        Dvd[] dvdsTemp = new Dvd[index];
        //将数组元素拷贝到此临时数组中
        for (int i = 0; i < index; i++) {
            dvdsTemp[i] = dvds[i];
        }

        Dvd dvdTemp;
        for (int i = 0; i < index; i++) {
            for (int j = 0; j < index-1; j++) {
                if(dvdsTemp[j].getCost()>dvdsTemp[j+1].getCost()){
                    //下标也要刷新
                    dvdsTemp[j].setIndex(j+1);
                    dvdsTemp[j+1].setIndex(j);

                    dvdTemp = dvdsTemp[j];
                    dvdsTemp[j] = dvdsTemp[j+1];
                    dvdsTemp[j+1] = dvdTemp;
                }
            }
        }
        //排序后输出
        System.out.println("按照单价升序：");
        printDvdArray(dvdsTemp);
    }

    /**
     * 通过热度降序
     */
    public void sortByTimes() {
        int index = Tools.getFreePosition(dvds);
        //给临时数组
        Dvd[] dvdsTemp = new Dvd[index];
        //将数组元素拷贝到此临时数组中
        for (int i = 0; i < index; i++) {
            dvdsTemp[i] = dvds[i];
        }

        Dvd dvdTemp;
        for (int i = 0; i < index; i++) {
            for (int j = 0; j < index-1; j++) {
                if(dvdsTemp[j].getTimes()<dvdsTemp[j+1].getTimes()){
                    //下标也要刷新
                    dvdsTemp[j].setIndex(j+1);
                    dvdsTemp[j+1].setIndex(j);

                    dvdTemp = dvdsTemp[j];
                    dvdsTemp[j] = dvdsTemp[j+1];
                    dvdsTemp[j+1] = dvdTemp;
                }
            }
        }
        //排序后输出
        System.out.println("按照热度降序：");
        printDvdArray(dvdsTemp);
    }


    /**
     * 通过关键词查找电影，返回数组
     * @param kyWords
     * @return
     */
    public Dvd[] searchByKeyWords(String kyWords){
        //遍历判断
        Dvd[] dvd = new Dvd[10];
        int index01=0;
        int index = Tools.getFreePosition(dvds);
        for (int i = 0; i < index; i++) {
            if(dvds[i].getName().contains(kyWords)){
                dvd[index01++] = dvds[i];
            }
        }
        return dvd;
    }




}
