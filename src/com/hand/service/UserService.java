package com.hand.service;

import com.hand.bean.User;
import com.hand.dao.UserDao;

import java.sql.SQLException;

public class UserService {

    //处理业务逻辑
    //1、判断注册的用户名是否存在
    public boolean register(String name,String password,String email) {
        boolean registers = false;
        //判断用户是否存在，需要在DAO层创建UserDao用它来操作数据库
        UserDao userDao = new UserDao();
        boolean checkUser = userDao.checkUser(name);
        //如果用户不存在，就将信息添加到数据库中
        System.out.println("user.getUserName():"+name);

        if (checkUser) {
            registers = userDao.register(name,password,email);
        }
        return registers;
    }

    //用户登陆
    public User login(String name, String password) throws SQLException {
        //查看是否登陆成功，需要操作数据库
        UserDao userDao = new UserDao();
        User login = userDao.login(name, password);
        return login;


    }


}
