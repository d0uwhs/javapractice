package com.minjun.javapractice.servletpractice.server;

import com.minjun.javapractice.servletpractice.server.servlet.HttpServlet;
import com.minjun.javapractice.servletpractice.server.servlet.HttpServletRequest;
import com.minjun.javapractice.servletpractice.server.servlet.HttpServletResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class MainServer {
    private Map<String, HttpServlet> servletMap;
    private Properties mapping;

    public MainServer() {
        /**
         * 서버 경로 매핑할 Map을 정의하고,
         * 경로 매핑할 클래스 경로를 담은 파일을 로드합니다.
         */
        servletMap = new HashMap<>();
        mapping = new Properties();

        try {
            /**
             * 매핑 파일을 로드합니다.
             */
            mapping.load(new FileInputStream("./mapping.properties"));
            /**
             * 불러온 매핑 파일을 forEach stream으로 프로퍼티값을 가져옵니다.
             */
            mapping.keySet().forEach((url) -> {
                String getServlets = mapping.getProperty(url.toString());
                try {
                    /**
                     *
                     */
                    Class loadServlet = Class.forName(getServlets);
                    HttpServlet httpServlet = (HttpServlet) loadServlet.getConstructor(null).newInstance(null);
                    httpServlet.init();
                    servletMap.put(url.toString(), httpServlet);
                } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException |
                         IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void runServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);

            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try {
                        InputStream inputStream = socket.getInputStream();
                        OutputStream outputStream = socket.getOutputStream();
                        Scanner scanner = new Scanner(inputStream);
                        HttpServletRequest httpServletRequest = new HttpServletRequest(inputStream);
                        HttpServletResponse httpServletResponse = new HttpServletResponse(outputStream);

                        String firstLine = scanner.nextLine();
                        String target = firstLine.split(" ")[1];

                        try {
                            HttpServlet httpServlet = servletMap.get(target);
                            httpServlet.service(httpServletRequest, httpServletResponse);
                        } catch (Exception e) {
                            outputStream.write("HTTP/1.1 500 Internal Server Error\r\n".getBytes());
                        }
                        inputStream.close();
                        outputStream.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
