package com.yicj.demo.stream;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class StreamDemo4 {

    public static void main(String[] args) {

        test001() ;

    }

    private static void test001(){

        Stream<String> stream =
            Stream.of("d2","a2","b1","b3","c")
            .filter(s -> s.startsWith("a")) ;
        stream.anyMatch(s -> true) ; // ok
        stream.noneMatch(s->true) ;  // exception
    }
    //为了克服这个限制，我们必须为我们想要执行的每个终端操作创建一个新的流链，
    // 例如，我们可以通过 Supplier 来包装一下流，通过 get()
    // 方法来构建一个新的 Stream 流
    //通过构造一个新的流，来避开流不能被复用的限制, 这也是取巧的一种方式。
    private static void test002(){

        Supplier<Stream<String>> streamSupplier =
                ()->Stream.of("d2","a2","b1","b3","c")
                .filter(s->s.startsWith("a")) ;
        streamSupplier.get().anyMatch(s -> true) ;//ok
        streamSupplier.get().noneMatch(s -> true) ;//ok
    }

}
