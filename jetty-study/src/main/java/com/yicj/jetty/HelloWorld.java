package com.yicj.jetty;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloWorld {

    public static void main(String[] args) throws Exception{
        Server server = new Server(8989);
        server.setHandler(new JettyHandler());
        server.start();
        server.join();
    }

    static class JettyHandler extends AbstractHandler {
        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request,
                           HttpServletResponse response) throws IOException, ServletException {
            System.out.println("target : " + target);
            response.setContentType("text/html;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            baseRequest.setHandled(true);
            PrintWriter out = response.getWriter();
            if (target.equals("/favicon.ico")) {
                out.println("404");
            } else {
                out.println("hello jetty");
                if (request.getParameter("name") != null) {
                    out.println(request.getParameter("name"));
                }
            }
        }
    }
}
