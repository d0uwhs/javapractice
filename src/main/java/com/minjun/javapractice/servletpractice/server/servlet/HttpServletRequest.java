package com.minjun.javapractice.servletpractice.server.servlet;

import java.io.InputStream;

public class HttpServletRequest {
    private InputStream inputStream;

    public HttpServletRequest(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
