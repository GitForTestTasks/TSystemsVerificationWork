package ru.tsystemsverificationwork.web.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;


@ControllerAdvice
public class ErrorHandler {


//    @ExceptionHandler(DataAccessException.class)
//    public String databaseExceptionHandler(DataAccessException ex) {
//
//        return "error";
//    }
//
//    @ExceptionHandler(AccessDeniedException.class)
//    public String AccessDeniedHandler(AccessDeniedException ex) {
//
//        return "denied";
//    }

//    @ExceptionHandler(Exception.class)
//    public String AccessDeniedHandler(Exception ex) {
//
//        return "unexpectederror";
//    }

}
