package com.yicj.stream.s2;

import java.util.Arrays;
import java.util.List;

public class EmployDemo2 {

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Justin",39,Gender.MALE),
                new Employee("Monica",36,Gender.FEMALE),
                new Employee("Irene",6,Gender.FEMALE)
        ) ;

        int sum = employees.stream()
                .filter(employee -> employee.getGender() == Gender.MALE)
                .mapToInt(Employee::getAge)
                .reduce((total,age)-> total + age)
                .getAsInt() ;

        long males = employees.stream()
            .filter(employee -> employee.getGender() == Gender.MALE)
            .count();

        long average = employees.stream()
                .filter(employee -> employee.getGender() == Gender.MALE)
                .mapToInt(Employee::getAge)
                .reduce((total,age) -> total + age)
                .getAsInt() / males ;

        int max = employees.stream()
                .filter(employee -> employee.getGender() == Gender.MALE)
                .mapToInt(Employee::getAge)
                .reduce(0,(currMax,age) -> age > currMax? age:currMax) ;


        Arrays.asList(sum,average,max).forEach(System.out::println);
    }


}




