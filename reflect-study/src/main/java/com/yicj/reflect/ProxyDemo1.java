package com.yicj.reflect;
import java.util.Scanner;

import com.yicj.common.util.LoggingHandler;


public class ProxyDemo1 {

    public static void main(String[] args) {
        LoggingHandler<Hello> loggingHandler = new LoggingHandler<>() ;
        Hello helloProxy = loggingHandler.bind(new HelloSpeaker()) ;
        helloProxy.hello("Justin");

        Scanner scanner = new Scanner(System.in) ;
        System.out.print("请输入：");
        String input =  scanner.next() ;

        System.out.println("你输入："+input);

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
