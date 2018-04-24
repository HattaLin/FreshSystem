package com.hand.service;

import com.hand.bean.Category;
import com.hand.bean.Page;
import com.hand.dao.CategoryDao;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class CategoryService {
    //CategoryDao categoryDao = new CategoryDao();
    public boolean addCategory(Category category) throws SQLException {
        CategoryDao dao = new CategoryDao();
        boolean addCategory = dao.addCategory(category);
        return addCategory;
    }

    public List<Category> SerachCategory() throws SQLException {
        CategoryDao dao = new CategoryDao();
        List<Category> categoryList = dao.SearchCategory();
        return categoryList;
    }


    public Page SerachPageCategory(int currentPage, int currentCount) throws SQLException {

       /* private int totalPage; //总页数
        private int currentPage; //当前页数
        private int currentCount; //当前页显示数目
        private int totalCount; //总共的数目*/
        Page page = new Page();
        CategoryDao dao = new CategoryDao();
        //1、计算出生鲜的总数
        int totalCount = dao.queryCount();
        //2、根据总数和当前页显示数，计算总页数
        int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
        //3、将所有的属性设置到bean里面，分页相关信息封装
        page.setCurrentCount(currentCount);
        page.setCurrentPage(currentPage);
        page.setTotalCount(totalCount);
        page.setTotalPage(totalPage);

        //4、计算查询的起始位置
        int startPosition = (currentPage - 1) * currentCount;
        //5、按照分页查询数据
        List<Category> categoryList1 = dao.SearchPageCategory(startPosition, currentCount);
        //6、将集合封装到Page中
        page.setList(categoryList1);

        //7、返回一个page类
        return page;
    }


    public boolean updateCategory(Category category) throws SQLException {
        CategoryDao categoryDao = new CategoryDao();
        boolean b = categoryDao.updateCategory(category);
        return b;

    }

    public boolean deleteCategory(int c_id) throws SQLException {
        CategoryDao categoryDao = new CategoryDao();
        boolean b = categoryDao.deleteCategory(c_id);
        return b;

    }
}
