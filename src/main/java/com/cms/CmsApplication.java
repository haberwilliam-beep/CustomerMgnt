package com.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.cms.mapper")
@EnableAspectJAutoProxy
@EnableScheduling
public class CmsApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}
