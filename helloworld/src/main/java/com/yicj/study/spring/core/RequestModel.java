package com.yicj.study.spring.core;

import lombok.Data;

import java.util.HashMap;

@Data
public class RequestModel extends HashMap<String,Object> {

    private String url ;

}
