package com.hand.web;

import com.hand.bean.User;
import com.hand.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet(name = "UserServlet", urlPatterns = "/user")
public class UserServlet extends BaseServlet {
   /* protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取到method方法
        String method = request.getParameter("method");
        if(method.equals("login")){
            System.out.println(1);
            login(request,response);
            System.out.println(2);
        }else if(method.equals("register")){
            register(request,response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }*/

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取参数
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        UserService userService = null;
        User login = null;
        //2、获取到参数，将参数传递给Service层
        System.out.println("name:"+name);
        System.out.println("password"+password);
        try {
            System.out.println("a");
            userService = new UserService();

            //要在这里判断，这个方法有返回值的，返回了user对象，是要判断user对象是否为null的
            System.out.println("b");
            login = userService.login(name, password);
            System.out.println("b.1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //这里错了，你在上面New UserService 那么userService就不会为null了就可以一直登陆成功了
        System.out.println("c");
        if (login != null) {
            System.out.println("获取到值");
            //登陆成功之后，可以获取到checkbox的状态
            String remember = request.getParameter("remember");
            //取到的值为yes的话，说明用户勾选了记住密码这个选项
            System.out.println("remember" + remember);
            if (remember == null || remember.equals("no")) {
                remember = "no";
            } else {
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
            //登陆成功之后将用户的信息保存到session
            request.getSession().setAttribute("user",login);

            //登陆成功，跳转到生鲜种类界面
            //response.sendRedirect(request.getContextPath() + "category-list.jsp");
            response.sendRedirect(request.getContextPath()+"/category?method=getCategoryList");
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("登陆失败");
        }


    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        System.out.println("name" + name);
        User user = new User();
        //直接将获取到的值封装成一个对象,拿到map集合，将map集合封装到对象中
        Map<String, String[]> parameterMap = request.getParameterMap();


        //Web层是用来接收数据的，接收到数据之后需要将这些数据传送给service层，service层具体做业务的处理

        try {
            //分别将属性设置到对象
            //BeanUtils.setProperty(user,name,name);
            //将属性的map集合封装到对象中

            BeanUtils.populate(user, parameterMap);
            System.out.println(1 + user.getUserName());
            System.out.println(2 + user.geteMail());
            System.out.println(3 + user.getPassWord());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        UserService userService = new UserService();
        boolean register = userService.register(name, password, email);
        if (register) {
            //注册成功，跳转到注册界面
            //通过重定向进行转达
            response.sendRedirect(request.getContextPath() + "login.jsp");
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("注册失败");
        }

    }
}
