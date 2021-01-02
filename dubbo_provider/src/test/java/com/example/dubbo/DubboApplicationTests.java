package com.example.dubbo;

import com.example.dubbo.service.TestService;
import com.example.dubbo.service.impl.TestServiceImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

@SpringBootTest
class DubboApplicationTests {

    @Test
    void contextLoads() throws FileNotFoundException {
        final String path = ResourceUtils.getURL("classpath:").getPath();
        System.out.println(path);
        final String path1 = this.getClass().getResource("/").getPath();
        System.out.println(path1);
    }

}
