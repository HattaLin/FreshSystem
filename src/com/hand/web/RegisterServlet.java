package com.hand.web;

import com.hand.bean.User;
import com.hand.service.UserService;
import org.apache.commons.beanutils.BeanUtils;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;


@WebServlet(name = "RegisterServlet",urlPatterns = "/dregister")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //获取参数
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        System.out.println("name"+name);
        User user = new User();
        //直接将获取到的值封装成一个对象,拿到map集合，将map集合封装到对象中
        Map<String, String[]> parameterMap = request.getParameterMap();


        //Web层是用来接收数据的，接收到数据之后需要将这些数据传送给service层，service层具体做业务的处理

        try {
            //分别将属性设置到对象
            //BeanUtils.setProperty(user,name,name);
            //将属性的map集合封装到对象中

            BeanUtils.populate(user,parameterMap);
            System.out.println(1+user.getUserName());
            System.out.println(2+user.geteMail());
            System.out.println(3+user.getPassWord());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        UserService userService = new UserService();
        boolean register = userService.register(name,password,email);
        if (register) {
            //注册成功，跳转到注册界面
            //通过重定向进行转达
            response.sendRedirect(request.getContextPath()+"login.jsp");
        }else {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("注册失败");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
