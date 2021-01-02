package com.example.dubbo.controller;

import com.example.dubbo.service.TestService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Louyp
 * @version 1.0
 * @data 2020/07/03 13:02
 */
@RestController
public class TestController {
    @Reference
    private TestService testService;
    @RequestMapping("/sayHi")
    public String sayHi(){
        for(int i=0;i<100;i++){
            testService.sayHi();
        }

        return "ok" ;
    }

}
