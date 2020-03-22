package com.yicj.thread.hello1;

//只有方法才能被override，变量不行。调用Father.show()时，编译时已经把this.name绑定到Father.name了
//1. Son 类没有override Father 类 show()方法，因此，调用的是Father类的show()方法；
//2. Father类的show()方法中第一个println()方法的参数为对象，属于运行时绑定，
//   第二个println()方法的参数为Father类字段，属于编译时绑定；
public class Animal <T extends Animal>{
    public String name = "我是父类的name";

    public T setName(String name) {
        this.name = name;
        return (T)this;
    }

    public void show() {
        System.out.println(this);
        System.out.println(this.name);
    }
}
