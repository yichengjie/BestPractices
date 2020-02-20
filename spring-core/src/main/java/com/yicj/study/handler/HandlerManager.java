package com.yicj.study.handler;

import com.yicj.study.annotation.Controller;

import javax.print.attribute.standard.Media;
import java.lang.reflect.Method;
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

    }
}
