package com.yicj.thread.spring.core;

import lombok.Data;

import java.lang.reflect.Method;

@Data
public class MappingHandler {

    private String url ;
    private Class<?> controllerClass ;
    private Method method ;
    private String [] paramNames ;

    public MappingHandler(String url,Class<?> clz, Method method, String [] paramNames){
        this.url = url ;
        this.controllerClass = clz ;
        this.method = method ;
        this.paramNames = paramNames ;
    }


    public boolean handler(RequestModel requestModel){
        if(!this.url.equals(requestModel.getUrl())){
            return false;
        }
        //如果url相同则处理请求
        Object bean = SpringUtils.getBean(controllerClass) ;
        try {
            method.setAccessible(true);
            method.invoke(bean, this.getParamValue(requestModel)) ;
        } catch (Exception e) {
           throw new RuntimeException(e) ;
        }
        return true ;
    }



    private Object[] getParamValue(RequestModel requestModel){
        Object [] values = new Object[this.paramNames.length] ;
        for(int i = 0 ; i < paramNames.length ; i++){
            Object o = requestModel.get(paramNames[i]);
            values[i] = o ;
        }
        return values ;
    }
}
