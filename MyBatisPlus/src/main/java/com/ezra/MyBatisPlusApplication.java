package com.ezra;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ezra")
public class MyBatisPlusApplication {


    public static void main(String[] args) {
        SpringApplication.run(MyBatisPlusApplication.class,args);
    }
}
