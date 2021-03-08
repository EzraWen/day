package com.ezra.controller;


import com.ezra.constant.SizeEnum;
import com.ezra.constant.SystemConstant;
import com.ezra.entity.File;
import com.ezra.response.Result;
import com.ezra.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Ezra Wen
 * @since 2021-03-08
 */
@RestController
@RequestMapping(SystemConstant.API_URL + "/file")
public class FileController {

    @Autowired
    private IFileService fileService;


    public Result add() {
        File file = new File();
        file.setSize(SizeEnum.SMALL);
        fileService.save(file);
        return Result.SUCCESS;
    }
}

