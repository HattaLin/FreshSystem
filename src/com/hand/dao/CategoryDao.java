package com.hand.dao;

import com.hand.bean.Category;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class CategoryDao {

    //创建一个方法用来插入数据
    public boolean addCategory(Category category) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        String sql = " insert into t_fresh_type values(null,?,?,?,?)";
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        int row = queryRunner.update(sql, category.getC_name(), category.getPlace(), category.getC_create_date(), category.getType());
        if (row > 0) {
            return true;
        } else {

            return false;
        }

    }

    public List<Category> SearchCategory() throws SQLException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        String sql = " select * from t_fresh_type ";
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        List<Category> categoryList = queryRunner.query(sql, new BeanListHandler<Category>(Category.class));
        return categoryList;

    }

    public int queryCount() throws SQLException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        String sql = " select count(*) from t_fresh_type ";
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        Long query = queryRunner.query(sql, new ScalarHandler<>());//ScalarHandler 聚合函数用来接收数据的方法
        return query.intValue();
    }

    public List<Category> SearchPageCategory(int startPosition, int currentCount) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        String sql = " select * from t_fresh_type limit ?,? ";
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        List<Category> categoryList = queryRunner.query(sql, new BeanListHandler<Category>(Category.class), startPosition, currentCount);
        return categoryList;

    }

    public boolean updateCategory(Category category) throws SQLException {
        ComboPooledDataSource source = new ComboPooledDataSource();
        String sql = " update t_fresh_type set c_name = ? , place = ? ,type = ? " +
                "where c_id = ? ";
        QueryRunner runner = new QueryRunner(source);
        int row = runner.update(sql, category.getC_name(), category.getPlace(), category.getType(), category.getC_id());
        if (row > 0) {

            return true;
        } else {
            return false;
        }

    }

    public boolean deleteCategory(int c_id) throws SQLException {
        ComboPooledDataSource source = new ComboPooledDataSource();
        String sql = " delete from t_fresh_type where c_id = ? ";
        QueryRunner runner = new QueryRunner(source);
        int row = runner.update(sql, c_id);
        if (row > 0) {

            return true;
        } else {
            return false;
        }
    }
}
