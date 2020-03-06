package com.yicj.study;

import com.yicj.study.bean.BeanFactory;
import com.yicj.study.handler.HandlerManager;
import com.yicj.study.handler.MappingHandler;
import com.yicj.study.scanner.ClassScanner;
import com.yicj.study.server.TomcatServer;
import java.util.List;

public class MiniApplication {

    public static void run(Class<?> cls, String[] args){
        TomcatServer tomcatServer = new TomcatServer(args);
        tomcatServer.startServer();
        List<Class<?>> classes = ClassScanner.scan(cls.getPackage().getName());
        BeanFactory.initBean(classes);
        HandlerManager.resolveMappingHandler(classes); ;
        //classes.forEach(clazz -> System.out.println(clazz));
        List<MappingHandler> mappingHandlerList = HandlerManager.mappingHandlerList;
    }
}
