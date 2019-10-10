package com.yicj.lambda;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@FunctionalInterface
interface  Func<P, R> {
    R apply(P p1, P p2) ;
    //void add() ;
}

class A{
    public int byLength(String name1, String name2){

        return name1.length() - name2.length() ;
    }
}


public class MethodReference {

    public static void main(String [] args){

        A a = new A() ;

        Func<String,Integer> func = (name1, name2) -> name1.length() - name2.length() ;

        Comparator<String> tt = a::byLength ;

        Comparator<String> comparator = (name1, name2) -> name1.length() - name2.length() ;

        PrintStream out = System.out ;

        String [] names = {"Justin", "caterpillar", "Bush"} ;

        List<String> nameList = Arrays.asList("Justin", "caterpillar", "Bush") ;
        nameList.forEach(name-> out.println(name));
        nameList.forEach(out::println);

    }

}
