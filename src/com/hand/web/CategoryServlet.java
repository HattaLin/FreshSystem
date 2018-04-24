package com.hand.web;

import com.hand.bean.Category;
import com.hand.bean.Page;
import com.hand.service.CategoryService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.*;

@WebServlet(name = "CategoryServlet", urlPatterns = "/category")
public class CategoryServlet extends BaseServlet {

    //增加生鲜种类
    public void addCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // request.setCharacterEncoding("utf-8");

        //1、获取页面传过来的值
        Map<String, String[]> parameterMap = request.getParameterMap();
        Boolean b = false;


        //2、创建实体类
        Category category = new Category();
        category.setC_create_date(new Date());
        //3、使用BeanUtils方法
        try {
            BeanUtils.populate(category, parameterMap);
            //4、创建CategoryService 用来处理业务逻辑
            CategoryService categoryService = new CategoryService();


            b = categoryService.addCategory(category);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       /* String c_name = request.getParameter("c_name");
        String c_place = request.getParameter("place");
        String c_type = request.getParameter("type");*/


        if (b) {
            //添加成功，给response设置一个status，jsp中有个status方法，它会按照status去显示不同的界面
            response.setStatus(201);
            //请求转发到主界面
            request.getRequestDispatcher("/category-add.jsp").forward(request, response);

        } else {
            //添加失败
            response.setStatus(600);
            request.getRequestDispatcher("/category-add.jsp").forward(request, response);

        }


    }

    //查询生鲜种类
    public void getCategoryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1、调用service中的查询方法
        CategoryService service = new CategoryService();
        int currentPage;
        int currentCount;
        try {
            //1、获取当前页数
            String currentPages = request.getParameter("currentPage");
            String currentCounts = request.getParameter("currentCount");
            if (currentPages == null || currentPages.equals("0")) {
                currentPage = 1;

            } else {
                currentPage = Integer.parseInt(currentPages);
            }
            if (currentCounts == null || currentCounts.equals("0")) {
                currentCount = 10;

            } else {
                currentCount = Integer.parseInt(currentCounts);
            }

            //int currentPage = Integer.parseInt(request.getParameter("currentPage"));
            //int currentCount = Integer.parseInt(request.getParameter("currentCount"));
            //分页数据设置默认值，把这个写在上面，然后先对request.getParameter("currentPage") 和request.getParameter("currentCount")为null，


            Page page = service.SerachPageCategory(currentPage, currentCount);

            if (page != null) {
                System.out.println("查询到数据！");
                //拿到数据，将数据返回给前端界面
                //将list值传递到域对象中
                request.setAttribute("page", page);
                //将界面转发过去
                request.getRequestDispatcher("/category-list.jsp").forward(request, response);

            } else {
                System.out.println("没有查询到数据！");

                request.getRequestDispatcher("/category-list.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //修改生鲜信息
    public void updateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {
            Map<String, String[]> parameterMap = request.getParameterMap();
            Category category = new Category();
            BeanUtils.populate(category, parameterMap);
            CategoryService service = new CategoryService();
            boolean b = service.updateCategory(category);
            if (b) {
                //修改成功之后，重定向到生鲜列表的界面
                response.sendRedirect(request.getContextPath() + "/category?method=getCategoryList");

            } else {
                //失败了直接提示
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("修改失败");
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        //删除生鲜信息
    public void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {
            int c_id = Integer.parseInt(request.getParameter("c_id"));
            CategoryService service = new CategoryService();
            boolean b = service.deleteCategory(c_id);
            if (b) {
                //删除成功之后，重定向到生鲜列表的界面
                response.sendRedirect(request.getContextPath() + "/category?method=getCategoryList");

            } else {
                //失败了直接提示
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("删除失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }




}