package com.yicj.study;

import java.io.UnsupportedEncodingException;
import java.nio.Buffer;
import java.nio.ByteBuffer;

public class ByteBufferHello {

    public static void main(String[] args) throws UnsupportedEncodingException {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put("abc".getBytes("UTF-8")) ;
        printInfo(buffer);
        buffer.flip();
        printInfo(buffer);
        byte a1 = buffer.get();
        buffer.mark();
        byte a2 = buffer.get();
        byte a3 = buffer.get();
        buffer.reset() ;
        System.out.println("b : " + (char)a1);
        System.out.println("b : " + (char)a2);
        System.out.println("b : " + (char)a3);
        printInfo(buffer);
        System.out.println((char)buffer.get()); ;
    }

    private static void printInfo(ByteBuffer buffer){
        int position = buffer.position();
        int capacity = buffer.capacity();
        int limit = buffer.limit();
        System.out.println("----------------------------");
        System.out.println("position : " + position);
        System.out.println("capacity : " + capacity);
        System.out.println("limit : " + limit);
    }
}
