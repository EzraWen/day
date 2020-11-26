package com.ezra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ezra")
public class ElasticsearchApplication {


    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchApplication.class, args);
    }
}

//
//PUT /my_index/_doc/3?routing=1
//{
//    "text": "This is an answer",
//    "my_join_field": {
//    "name": "answer",
//    "parent": "1"
//}