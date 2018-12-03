package com.primeton.liuning.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan({"com.primeton.liuning.demo.dao"})
@EnableSwagger2
@EnableTransactionManagement
public class LiuNingDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiuNingDemoApplication.class, args);
    }
}
