package com.codecool.webroute;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;

public class Test {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        Class<Requests> obj = Requests.class;
        for (Method method : obj.getDeclaredMethods()) {
            if (method.isAnnotationPresent(WebRoute.class)) {
                Annotation annotation = method.getAnnotation(WebRoute.class);
                WebRoute test = (WebRoute) annotation;
                if (test.value().equals("/first")) {
                    server.createContext(test.value(), new Requests());
                    server.setExecutor(null); // creates a default executor
                    server.start();
                }
            }
        }
    }
}
