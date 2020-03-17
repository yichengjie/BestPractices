package com.yicj.thread.spring.core;

import com.yicj.thread.spring.core.annotation.Controller;
import com.yicj.thread.spring.core.annotation.RequestMapping;
import com.yicj.thread.spring.core.annotation.RequestParam;
import com.yicj.thread.spring.test.controller.UserController;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class HandlerManager {

    public static List<MappingHandler> mappingHandlerList = new ArrayList<>() ;

    public static void main(String[] args) {
        List<MappingHandler> handlers =
                HandlerManager.parseHandlerFormController(UserController.class);
        System.out.println(handlers);
    }

    public static void resolveMappingHandler(List<Class<?>> classList){
        for (Class<?> clazz : classList){
            List<MappingHandler> handlers = parseHandlerFormController(clazz);
            mappingHandlerList.addAll(handlers) ;
        }
    }

    /**
     * 从controller解析HandlerMapping对象
     * @param clazz
     */
    private static List<MappingHandler> parseHandlerFormController(Class<?> clazz) {
        List<MappingHandler> retList = new ArrayList<>() ;
        Controller controller = clazz.getDeclaredAnnotation(Controller.class);
        //如果是controller的话则解析各个方法
        if(controller == null){
           return retList ;
        }
        Method[] methods = clazz.getDeclaredMethods();
        for(Method method: methods){
            MappingHandler handler = parseMethod(method, clazz);
            addListValue(retList,handler) ;
        }
        return retList ;
    }


    private static MappingHandler parseMethod(Method method, Class<?> clazz){
        RequestMapping requestMapping =
                method.getDeclaredAnnotation(RequestMapping.class) ;
        if(requestMapping == null){
            return null;
        }
        String url = requestMapping.value() ;
        List<String> paramNameList = new ArrayList<>() ;
        Parameter[] parameters = method.getParameters();
        for(Parameter parameter: parameters){
            String paramName = parseParamName(parameter);
            addListValue(paramNameList, paramName);
        }
        String[] paramsNames = paramNameList.toArray(new String[0]);
        MappingHandler handler =
                new MappingHandler(url,clazz,method,paramsNames) ;
        return handler ;
    }

    private static String parseParamName(Parameter parameter){
        RequestParam requestParam =
                parameter.getDeclaredAnnotation(RequestParam.class);
        if(requestParam != null){
            return  requestParam.value() ;
        }
        return null ;
    }


    private static <T> void addListValue(List<T> list, T t){
        if(t != null){
            list.add(t) ;
        }
    }


}
