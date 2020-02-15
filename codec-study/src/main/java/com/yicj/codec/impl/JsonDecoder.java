package com.yicj.codec.impl;

import com.alibaba.fastjson.JSON;
import com.yicj.codec.Decoder;

public class JsonDecoder implements Decoder {
    @Override
    public <T> T decoder(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes,clazz);
    }
}
