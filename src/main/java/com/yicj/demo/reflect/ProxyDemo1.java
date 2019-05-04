package com.yicj.demo.reflect;
import com.yicj.demo.common.util.LoggingHandler;


public class ProxyDemo1 {

    public static void main(String[] args) {
        LoggingHandler<Hello> loggingHandler = new LoggingHandler<>() ;
        Hello helloProxy = loggingHandler.bind(new HelloSpeaker()) ;
        helloProxy.hello("Justin");
    }

    interface Hello {
         void hello(String name) ;
    }

    static class HelloSpeaker implements Hello {
        @Override
        public void hello(String name) {
            System.out.printf("哈喽,%s\n",name);
        }
    }
}
