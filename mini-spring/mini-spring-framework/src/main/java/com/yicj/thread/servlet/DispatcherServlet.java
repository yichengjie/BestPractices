package com.yicj.thread.servlet;

import com.yicj.thread.handler.HandlerManager;
import com.yicj.thread.handler.MappingHandler;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet implements Servlet {

    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        for (MappingHandler handler :HandlerManager.mappingHandlerList){
            if (handler.handle((HttpServletRequest) req,(HttpServletResponse) resp)){
                return;
            }
        }
        resp.getWriter().println("404"); ;
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
