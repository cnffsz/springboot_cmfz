package com.baizhi.interceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Interception  {
    @ExceptionHandler(value = RuntimeException.class)
    public String handException(){
        return "index";
    }
}
