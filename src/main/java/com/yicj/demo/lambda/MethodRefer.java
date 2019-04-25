package com.yicj.demo.lambda;

public class MethodRefer {

    public static void main(String[] args) {

        TestClassMethodRef ref = str -> System.out.println("str : " + str) ;
        ref.test("yicj");

        TestClassMethodRef ref1 = MethodRefer::output;
        ref1.test("yicj");

        MethodRefer mr = new MethodRefer() ;
        TestClassMethodRef ref2 = mr::output2;
        ref2.test("yicj");

        TestConstructorRef ref3 = ()-> new User() ;
        ref3.getUser() ;
        TestConstructorRef ref4 = User::new ;
        ref4.getUser() ;
    }



    public void output2(String str){

        System.out.println("str : " + str);
    }

    public static void output(String str){
        System.out.println("str : " + str);
    }

}


interface TestClassMethodRef{
    void test(String str) ;
}

interface TestConstructorRef{
    User getUser() ;
}

class User{
    public User(){
        System.out.println("User 的构造器被调用!");
    }
}
