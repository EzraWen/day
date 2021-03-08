package com.ezra.controller;

import com.alibaba.fastjson.JSON;
import com.ezra.constant.SizeEnum;
import com.ezra.entity.File;
import com.ezra.service.IFileService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class FileControllerTest {


    @Autowired
    private IFileService fileService;

    @Test
    void add() {
        File file = new File();
        file.setSize(SizeEnum.SMALL);
        fileService.save(file);
    }


    @Test
    void get(){
        List<File> list = fileService.list();
        String json = JSON.toJSONString(list);
        System.out.println(json);
    }
}