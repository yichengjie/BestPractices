package com.yicj.stream.s1;

import java.util.ArrayList ;
import java.util.List;
import java.util.stream.IntStream;
import com.yicj.stream.Model;

public class StreamDemo6 {

    public static void main(String[] args) {
       //test001() ;
        test002() ;
    }


    private static void test001(){
        List<Model.Foo> foos = new ArrayList<Model.Foo>() ;
        //创建foos集合
        IntStream.range(1,4).forEach(i->foos.add(new Model.Foo("Foo" + i)));

        //创建bars集合
        foos.forEach(foo ->
            IntStream
                .range(1,4)
                .forEach(i-> foo.bars.add(new Model.Bar("Bar" + i +"<-" + foo.name))));

        foos.stream().flatMap(f->f.bars.stream())
        .forEach(bar -> System.out.println(bar.name));
    }


    private static void test002(){

        IntStream.range(1,4).mapToObj(i->new Model.Foo("Foo" + i))
        .peek(f->IntStream.range(1,4)
            .mapToObj(i->new Model.Bar("Bar" + i +"<-" + f.name))
            .forEach(f.bars::add))
        .flatMap(f->f.bars.stream())
        .forEach(bar -> System.out.println(bar.name));

    }
}

