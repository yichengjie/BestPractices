package com.yicj.lambda;


import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

class Person{
    private String name ;
    private String addr ;

    public Person(){

    }

    public Person(String name){
        this.name = name ;
    }

    public Person(String name ,String addr){
        this.name = name ;
        this.addr = addr ;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class ConstructorReference {
    private static PrintStream out = System.out ;

    static <P,R> List<R> map (List<P> list , Function<P,R> mapper){
        List<R> newList = new ArrayList<R>();
        for(P p : list){
            R r = mapper.apply(p) ;
            newList.add(r) ;
        }
        return newList ;
    }
    public static void main(String [] args){
        List<String> names = Arrays.asList("zhangsan", "lisi", "wangwu") ;
        //方式一：
        // List<Person> persons = map(names,name-> new Person(name)) ;
        //方式二：
        List<Person> persons = map(names, Person::new) ;
        persons.forEach(out::println);
    }
}
