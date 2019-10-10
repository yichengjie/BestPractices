package com.yicj.stream.s2;

public class Employee{

    private String name ;
    private Integer age ;
    private Gender gender ;

    public Employee(String name, Integer age , Gender gender){
        this.name = name ;
        this.age = age ;
        this.gender = gender ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}