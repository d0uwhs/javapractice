package com.minjun.javapractice.servletpractice.server.servlet;

import java.io.IOException;
import java.io.InputStream;

/**
 * 서블릿에 대한 공통적인 구현을 정의합니다.
 *
 *
 */
public abstract class HttpServlet {
    /**
     * init 라이프사이클 메소드를 정의합니다.
     */
    public abstract void init();

    public abstract void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException;
}
