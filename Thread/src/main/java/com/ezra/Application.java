package com.ezra;


import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ezra")
@MapperScan("com.ezra.*")
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
