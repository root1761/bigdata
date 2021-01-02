package com.example.dubbo.service.impl;

import com.example.dubbo.service.TestService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author Louyp
 * @version 1.0
 * @data 2020/07/03 11:33
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    public String sayHi() {
        System.out.println("dubbo-provider-2");

        return "sayHi";
    }
}
