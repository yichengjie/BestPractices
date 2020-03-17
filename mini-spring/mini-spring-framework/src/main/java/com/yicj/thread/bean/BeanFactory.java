package com.yicj.thread.bean;

import com.yicj.thread.annotation.AutoWired;
import com.yicj.thread.annotation.Bean;
import com.yicj.thread.annotation.Controller;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactory {
    private static Map<Class<?>,Object> classToBean = new ConcurrentHashMap<>() ;
    public static Object getBean(Class<?> cls){
        return classToBean.get(cls) ;
    }

    public static void initBean(List<Class<?>> classList){
        List<Class<?>> toCreate = new ArrayList<>(classList) ;
        while (toCreate.size() !=0){
            int remainSize = toCreate.size() ;
            Iterator<Class<?>> iter = toCreate.iterator();
            while (iter.hasNext()){
                Class<?> next = iter.next();
                if(finishCreate(next)){
                    iter.remove();
                }
            }
            if(toCreate.size() == remainSize){
                throw new RuntimeException("cycle dependency!") ;
            }
        }
    }

    private static boolean finishCreate(Class<?> cls) {
        if (!cls.isAnnotationPresent(Bean.class) &&
                !cls.isAnnotationPresent(Controller.class)){
            return true ;
        }
        try {
            Object bean = cls.newInstance() ;
            Field[] fields = cls.getDeclaredFields();
            for (Field field: fields){
                if (field.isAnnotationPresent(AutoWired.class)){
                    Class<?> fieldType = field.getType();
                    Object reliantBean = BeanFactory.getBean(fieldType);
                    if(reliantBean == null){
                        return false ;
                    }
                    field.setAccessible(true);
                    field.set(bean,reliantBean);
                }
            }
            classToBean.put(cls,bean) ;
            return true;
        }catch (Exception e){
            throw new RuntimeException("init bean error！",e) ;
        }
    }
}
