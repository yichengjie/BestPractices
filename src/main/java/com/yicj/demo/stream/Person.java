package com.yicj.demo.stream;

import java.util.Arrays;
import java.util.List;

public class Person {

    String name ;
    int age ;

    Person(String name, int age){
        this.name = name ;
        this.age = age ;
    }

    @Override
    public String toString() {
        return name ;
    }

    public static List<Person> getDBPerson(){
        List<Person> personList = Arrays.asList(
            new Person("Max",18) ,
            new Person("Perter",23),
            new Person("Pamela",23),
            new Person("David",12)
        ) ;
        return personList ;
    }
}
