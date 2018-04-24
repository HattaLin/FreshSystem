package com.hand.web;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet(name = "BaseServlet")
public class BaseServlet extends HttpServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        try {
            //1、获取方法名
            String method = req.getParameter("method");
            //2、获取当前对象的字节码文件

            Class aclass = this.getClass();

            //3、拿到对象里的方法

            Method classMethod = aclass.getMethod(method, HttpServletRequest.class, HttpServletResponse.class);

            //4、执行方法
            classMethod.invoke(this, req, res);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }
}
