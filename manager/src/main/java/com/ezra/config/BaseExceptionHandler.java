package com.ezra.config;


import com.ezra.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
@Slf4j
public class BaseExceptionHandler {


    @ExceptionHandler(Exception.class)
    public Result baseException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.fail(e.getMessage());
    }

    /**
     * 参数校验不通过的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result methodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        log.error(e.getMessage(),e);
        return Result.fail(bindingResult.getFieldError().getDefaultMessage());
    }


}
