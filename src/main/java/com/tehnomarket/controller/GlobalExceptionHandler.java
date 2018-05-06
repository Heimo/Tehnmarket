package com.tehnomarket.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value=Exception.class)
    public String error(Model m,Exception e) {
        m.addAttribute("error", e.getMessage());
        return "error";
    }

}
