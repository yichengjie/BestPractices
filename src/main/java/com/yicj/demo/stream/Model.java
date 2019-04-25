package com.yicj.demo.stream;

import java.util.ArrayList;
import java.util.List;

public class Model {

    static class Foo {
        String name ;
        List<Bar> bars = new ArrayList<Bar>() ;

        Foo(String name){
            this.name = name ;
        }
    }

    static class Bar{
        String name ;
        Bar(String name){
            this.name = name ;
        }
    }
}
