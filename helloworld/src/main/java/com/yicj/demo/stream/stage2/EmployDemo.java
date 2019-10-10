package com.yicj.demo.stream.stage2;

import java.util.Arrays;
import java.util.List;

public class EmployDemo {

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Justin",39,Gender.MALE),
                new Employee("Monica",36,Gender.FEMALE),
                new Employee("Irene",6,Gender.FEMALE)
        ) ;

        int sum = employees.stream()
            .filter(employee -> employee.getGender() == Gender.FEMALE)
            .mapToInt(Employee::getAge)
            .sum() ;

        int average = (int)employees.stream()
            .filter(employee -> employee.getGender() == Gender.FEMALE)
            .mapToInt(Employee::getAge)
            .average()
            .getAsDouble() ;
        int max = employees.stream()
                .filter(employee -> employee.getGender() == Gender.FEMALE)
                .mapToInt(Employee::getAge)
                .max()
                .getAsInt() ;

        Arrays.asList(sum,average,max)
            .forEach(System.out::println);

    }


}




