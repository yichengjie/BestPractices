package com.yicj.methodref;

public class Student {

    private String name ;
    private Integer score ;

    public void setNameAndScore(String name, Integer score){
        this.name = name ;
        this.score = score ;
        System.out.println("Student " + name +"'s score is " + score);
    }

    public static void main(String[] args) {
        //lambda表达式的用法
        //TestInterface testInterface = (student, name, score) -> student.setNameAndScore(name, score);
        //类的任意对象的实例方法引用的用法
        TestInterface testInterface = Student::setNameAndScore ;
        testInterface.set(new Student(), "DoubleBin", 100) ;
    }

    @FunctionalInterface
    interface TestInterface{
        //注意：入参比Student类setNameAndScore方法多一个Student对象，除第一个外其他入参类型一致
        void set(Student s, String name, Integer score) ;
    }
}
