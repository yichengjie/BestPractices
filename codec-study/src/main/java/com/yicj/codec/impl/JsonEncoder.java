package com.yicj.codec.impl;

import com.alibaba.fastjson.JSON;
import com.yicj.codec.Encoder;

public class JsonEncoder implements Encoder {
    @Override
    public byte[] encoder(Object obj) {
        return JSON.toJSONBytes(obj);
    }
}
