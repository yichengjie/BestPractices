package com.yicj.thread.server;

import com.yicj.thread.servlet.DispatcherServlet;
import org.apache.catalina.Context;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

public class TomcatServer {

    private Tomcat tomcat ;
    private String [] args ;

    public TomcatServer(String [] args){
        this.args = args ;
    }

    public void startServer(){
        try {
            tomcat = new Tomcat() ;
            tomcat.setHostname("127.0.0.1");
            tomcat.setPort(8080);

            Context context = new StandardContext();
            context.setPath("");
            context.addLifecycleListener(new Tomcat.FixContextListener());

            DispatcherServlet servlet = new DispatcherServlet();

            Tomcat.addServlet(context, "dispatcherServlet", servlet).setAsyncSupported(true);
            context.addServletMappingDecoded("/", "dispatcherServlet");
            tomcat.getHost().addChild(context);
            tomcat.start();

            Thread awaitThread = new Thread("tomcat_await_thread"){
                @Override
                public void run() {
                    TomcatServer.this.tomcat.getServer().await();
                }
            };
            awaitThread.setDaemon(false);
            awaitThread.start();

        }catch (Exception e){
            throw new RuntimeException(e) ;
        }

    }


}
