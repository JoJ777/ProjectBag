package com.edward.DVD.dao;

import com.edward.DVD.entity.User;

import java.util.Scanner;
import java.util.UUID;

public class UserManager {
    static User[] users = new User[100];

    Scanner sc;
    User addUser(){
        sc = new Scanner(System.in);
        User user = new User();
        user.setId(UUID.randomUUID().toString().replace("-",""));
        System.out.println("请输入用户名：");
        user.setName(sc.nextLine());
        System.out.println("请输入电话号码：");
        user.setMobile(sc.next());

        return null;
    }
}
