package com.yicj.demo.stream;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamDemo5 {

    static List<Person> persons = Person.getDBPerson() ;

    public static void main(String[] args) {
        //test001() ;
        //test002() ;
        //test003() ;
        //test004() ;
        test005() ;
    }

    private static void test001(){

        List<Person> filtered =  persons.stream()//创建流
            .filter(person -> person.name.startsWith("P"))
            .collect(Collectors.toList()) ;
        System.out.println(filtered);
    }

    //age 18: [Max]
    //age 23: [Perter, Pamela]
    //age 12: [David]
    private static void test002(){
        Map<Integer,List<Person>> personByAge = persons.stream()
        .collect(Collectors.groupingBy(person -> person.age)) ;//以年龄为key，进行分组

        personByAge.forEach((age,p)-> System.out.format("age %s: %s\n",age,p));
    }

    //In Germany Max and Perter and Pamela are of legal age.
    //连接收集器的入参接受分隔符，以及可选的前缀以及后缀。
    private static void test003(){
        // 以 In Germany 开头，and 连接各元素，再以 are of legal age. 结束
        String phrase = persons
        .stream()
        .filter(person -> person.age >= 18)
        .map(p -> p.name) //提取出名字
        .collect(Collectors.joining(" and ","In Germany "," are of legal age."));

        System.out.println(phrase);
    }


    //{18=Max, 23=Perter;Pamela, 12=David}
    private static void test004(){
        Map<Integer,String> map =
        persons.stream().collect(Collectors.toMap(
            p -> p.age,
            p -> p.name,
            (name1,name2)-> name1 + ";" + name2//对于同样的key，将值拼接
        )) ;
        System.out.println(map);
    }


    private static void test005(){


        Collector<Person, StringJoiner, String> personNameCollector =
            Collector.of(
                () -> new StringJoiner(" | "),          // supplier 供应器
                (j, p) -> j.add(p.name.toUpperCase()),  // accumulator 累加器
                (j1, j2) -> j1.merge(j2),               // combiner 组合器
                StringJoiner::toString);                // finisher 终止器
        String names = persons.stream().collect(personNameCollector) ;//传入自定义的收集器

        System.out.println(names);
    }




}
