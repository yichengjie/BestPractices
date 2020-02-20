package com.yicj.study.handler;

import com.yicj.study.bean.BeanFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class MappingHandler {
    private String uri ;
    private Method method ;
    private Class<?> controller ;
    private String [] args ;

    public MappingHandler(String uri, Method method,Class<?> cls,String [] args){
        this.uri = uri ;
        this.method = method ;
        this.controller = cls ;
        this.args = args ;
    }

    public boolean handle(HttpServletRequest req, HttpServletResponse res){
        String requestUri = req.getRequestURI() ;
        if(!uri.equals(requestUri)){
            return false ;
        }
        Object [] parameters = new Object[args.length] ;
        for (int i =0 ; i < args.length ; i++){
            parameters[i] = req.getParameter(args[i]) ;
        }
        try {
            Object ctrl = BeanFactory.getBean(controller);
            Object ret = method.invoke(ctrl, parameters);
            res.getWriter().println(ret); ;
            return true ;
        }catch (Exception e){
            throw new RuntimeException(e) ;
        }
    }

}
