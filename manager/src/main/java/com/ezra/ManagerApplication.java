package com.ezra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ezra")
public class ManagerApplication {


    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class,args);
    }
}
