package com.minjun.javapractice.servletpractice.server.servlet.servlets;

import com.minjun.javapractice.servletpractice.server.servlet.HttpServlet;
import com.minjun.javapractice.servletpractice.server.servlet.HttpServletRequest;
import com.minjun.javapractice.servletpractice.server.servlet.HttpServletResponse;

import java.io.IOException;

public class HelloServlet extends HttpServlet {
    @Override
    public void init() {
        System.out.println("Init Servlet : " + this.getClass().getName());
    }

    @Override
    public void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType("text/html");
        httpServletResponse.print("<h1>Hello World!</h1>");

    }
}
