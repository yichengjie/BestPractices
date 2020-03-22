package com.yicj.thread.hello1;

public class Dog extends Animal<Dog> {

    String name = "我是子类的name";

    public void showName() {
        System.out.println(name);
    }
}
