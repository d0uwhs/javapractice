package com.minjun.javapractice.servletpractice.server.servlet;


import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Request 서블릿을 정의합니다.
 */
public class HttpServletResponse {

    private OutputStream outputStream;
//    private StringBuilder stringBuilder;

    public HttpServletResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
//        this.stringBuilder = new StringBuilder();

    }

    /**
     * HTTP 헤더 타입을 정의합니다.
     *
     * @param contentType 컨텐츠 타입
     * @throws IOException
     */
    public void setContentType(String contentType) throws IOException {
        outputStream.write("HTTP/1.1 200OK \r\n".getBytes());
        outputStream.write(("Content-Type: " + contentType + "\r\n\r\n").getBytes());
    }

    /**
     * HTTP 데이터를 출력합니다.
     *
     * @param message
     * @throws IOException
     */
    public void print(String message) throws IOException {
        outputStream.write(message.getBytes(StandardCharsets.UTF_8));
    }
}
