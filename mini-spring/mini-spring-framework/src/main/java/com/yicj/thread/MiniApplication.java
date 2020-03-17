package com.yicj.thread;

import com.yicj.thread.bean.BeanFactory;
import com.yicj.thread.handler.HandlerManager;
import com.yicj.thread.handler.MappingHandler;
import com.yicj.thread.scanner.ClassScanner;
import com.yicj.thread.server.TomcatServer;
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
