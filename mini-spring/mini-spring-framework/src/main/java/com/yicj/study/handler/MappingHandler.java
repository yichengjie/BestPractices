package com.yicj.study.handler;

import com.yicj.study.bean.BeanFactory;
import lombok.ToString;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@ToString
public class MappingHandler {
    private String uri;
    private Method method;
    private Class<?> controllerClass;
    private String[] paramNames;

    public MappingHandler(String uri, Method method, Class<?> cls, String[] paramNames) {
        this.uri = uri;
        this.method = method;
        this.controllerClass = cls;
        this.paramNames = paramNames;
    }

    public boolean handle(HttpServletRequest req, HttpServletResponse res) {
        String requestUri = req.getRequestURI();
        if (!uri.equals(requestUri)) {
            return false;
        }
        try {
            String[] paramValues = this.getParamValues(req);
            Object ctrl = BeanFactory.getBean(controllerClass);
            Object ret = method.invoke(ctrl, paramValues);
            res.getWriter().println(ret);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String[] getParamValues(HttpServletRequest req) {
        String[] parameters = new String[paramNames.length];
        for (int i = 0; i < paramNames.length; i++) {
            parameters[i] = req.getParameter(paramNames[i]);
        }
        return parameters;
    }

}
