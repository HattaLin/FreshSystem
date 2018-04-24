package com.hand.dao;

import com.hand.bean.User;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDao {
    //操作数据库，判断用户是否存在
    public boolean checkUser(String name) {
        try {
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql = "select username from t_uesr where username = ? ";
            User user = queryRunner.query(sql, new BeanHandler<User>(User.class), name);
            if (user == null) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean register(String name,String password,String email) {
        try {
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql = "insert into t_uesr values(null,?,?,?)";
            int row = queryRunner.update(sql, name, password, email);
            //行数大于0，说明注册成功
            if (row > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    //方法直接返回一个User对象，这样需要的信息我们可以直接使用
    public User login(String name, String password) throws SQLException {

        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        String sql = "select * from t_uesr where username = ? and password = ? ";
        User user = queryRunner.query(sql, new BeanHandler<User>(User.class), name, password);
        return  user;

    }
}
