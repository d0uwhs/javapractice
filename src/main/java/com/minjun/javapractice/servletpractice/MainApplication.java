package com.minjun.javapractice.servletpractice;

import com.minjun.javapractice.servletpractice.server.MainServer;

import java.io.File;

public class MainApplication {
    public static void main(String[] args) {
        MainServer mainServer = new MainServer();
        mainServer.runServer();
//        System.out.println(getClass().getResourceAsStream("/mapping.properties"));
    }
}
