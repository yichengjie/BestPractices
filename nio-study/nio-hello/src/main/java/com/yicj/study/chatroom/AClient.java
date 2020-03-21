package com.yicj.study.chatroom;

import java.io.IOException;

public class AClient {


    public static void main(String[] args) throws IOException {
        NioClient client = new NioClient() ;
        client.start("AClient");
    }

}
