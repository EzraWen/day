package com.ezra.config;


import com.ezra.exception.BusinessException;
import com.ezra.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
@Slf4j
public class BaseExceptionHandler {


    @ExceptionHandler(Exception.class)
    public Result baseException(Exception e){
        log.error(e.getMessage(),e);
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Result baseException(BusinessException e){
        log.error(e.getMsg());
        return Result.fail(e.getMsg());
    }


}
