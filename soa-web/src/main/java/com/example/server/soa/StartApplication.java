package com.example.server.soa;

import com.example.server.soa.common.util.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:config.properties")
public class StartApplication {

    public static void main(String[] args) {
        ApplicationContext act = SpringApplication.run(StartApplication.class, args);
        SpringUtil.setApplicationContext(act);
    }
}
