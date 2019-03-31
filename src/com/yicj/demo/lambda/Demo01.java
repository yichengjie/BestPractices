package com.yicj.demo.lambda;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@FunctionalInterface
interface  Func<P, R> {
    R apply(P p1, P p2) ;
    //void add() ;
}


public class Demo01 {

    public static void main(String [] args){

        Func<String,Integer> func = (name1, name2) -> name1.length() - name2.length() ;

        Comparator<String> comparator = (name1, name2) -> name1.length() - name2.length() ;

        PrintStream out = System.out ;

        String [] names = {"Justin", "caterpillar", "Bush"} ;

        List<String> nameList = Arrays.asList("Justin", "caterpillar", "Bush") ;
        nameList.forEach(name-> out.println(name));
        nameList.forEach(out::println);

    }

}
