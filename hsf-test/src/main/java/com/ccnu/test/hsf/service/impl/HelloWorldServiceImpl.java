package com.ccnu.test.hsf.service.impl;

import com.ccnu.test.hsf.service.HelloWorldService;

/**
 * Created by 龚元宝 on 2016/5/9.
 */
public class HelloWorldServiceImpl implements HelloWorldService {
    @Override
    public String sayHello(String name) {
        return "Hello "+name;
    }
}
