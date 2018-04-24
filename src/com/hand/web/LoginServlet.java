package com.hand.web;

import com.hand.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取参数
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        UserService userService = null;
        //2、获取到参数，将参数传递给Service层

        try {
            userService = new UserService();
            userService.login(name, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (userService != null) {
            //登陆成功之后，可以获取到checkbox的状态
            String remember = request.getParameter("remember");
            //取到的值为yes的话，说明用户勾选了记住密码这个选项
            System.out.println("remember"+remember);
            if(remember==null || remember.equals("no") ){
                remember = "no";
            }else{
                remember = remember;
            }
            if (remember.equals("yes")) {
                //将用户名和密码保存在cookie中
                Cookie cookie = new Cookie("name", name);
                Cookie cookie1 = new Cookie("password", password);
                //将cookie做一个持久化
                cookie.setMaxAge(60 * 10);
                cookie1.setMaxAge(60 * 10);
                response.addCookie(cookie);
                response.addCookie(cookie1);
                //然后在JSP界面登陆的时候拿到cookie信息
             }
            //登陆成功，跳转到生鲜种类界面
            response.sendRedirect(request.getContextPath() + "category-list.jsp");
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("登陆失败");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
