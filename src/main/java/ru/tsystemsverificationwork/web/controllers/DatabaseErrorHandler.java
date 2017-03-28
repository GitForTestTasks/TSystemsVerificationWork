package ru.tsystemsverificationwork.web.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class DatabaseErrorHandler {


    @ExceptionHandler(DataAccessException.class)
    public String databaseExceptionHandler(DataAccessException ex) {

        return "error";
    }

}
