package com.yicj.demo.stream.stage2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EmployDemo3 {

    public static void main(String[] args) {

        List<Employee> employees = Arrays.asList(
                new Employee("Justin",39,Gender.MALE),
                new Employee("Monica",36,Gender.FEMALE),
                new Employee("Irene",6,Gender.FEMALE)
        ) ;


        List<Employee> males =  employees.stream()
            .filter(employee -> employee.getGender() == Gender.MALE)
            .collect(Collectors.toList()) ;





    }
}
