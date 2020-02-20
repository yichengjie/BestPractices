package com.yicj.study.handler;

import com.yicj.study.annotation.Controller;
import com.yicj.study.annotation.RequestMapping;
import com.yicj.study.annotation.RequestParam;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class HandlerManager {
    public static List<MappingHandler> mappingHandlerList = new ArrayList<>() ;

    public static void resolveMappingHandler(List<Class<?>> classList){
        for (Class<?> cls : classList){
            if(cls.isAnnotationPresent(Controller.class)){
                parseHandlerFormController(cls) ;
            }
        }
    }

    private static void parseHandlerFormController(Class<?> cls) {
        Method [] methods = cls.getDeclaredMethods() ;
        for (Method method: methods){
            if(method.isAnnotationPresent(RequestMapping.class)){
                String uri = method.getDeclaredAnnotation(RequestMapping.class).value() ;
                List<String> paramNameList = parseMethodParamNames(method) ;
                String[] paramNameArr = paramNameList.toArray(new String[paramNameList.size()]);
                MappingHandler mappingHandler = new MappingHandler(uri, method, cls, paramNameArr);
                HandlerManager.mappingHandlerList.add(mappingHandler);
            }
        }
    }

    /**
     * 获取方法上的参数
     * @param method
     * @return
     */
    private static List<String> parseMethodParamNames(Method method){
        List<String> paramNameList = new ArrayList<>() ;
        for (Parameter parameter :method.getParameters()){
            if(parameter.isAnnotationPresent(RequestParam.class)){
                String parameterName = parameter.getDeclaredAnnotation(RequestParam.class).value();
                paramNameList.add(parameterName) ;
            }
        }
        return paramNameList ;
    }
}
