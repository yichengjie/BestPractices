package com.yicj.stream;

import java.util.ArrayList;
import java.util.List;

public class Model {

    public static class Foo {
        public String name ;
        public List<Bar> bars = new ArrayList<Bar>() ;

        public Foo(String name){
            this.name = name ;
        }
    }

    public static class Bar{
        public String name ;
        public Bar(String name){
            this.name = name ;
        }
    }
    
}
