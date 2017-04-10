package ru.andrei.tsystemsverificationwork.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.andrei.tsystemsverificationwork.web.exceptions.GenericException;

import java.nio.file.AccessDeniedException;


@ControllerAdvice
public class ErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler(DataAccessException.class)
    public String databaseExceptionHandler(DataAccessException ex) {

        log.error(ex.getMessage());
        return "error";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String AccessDeniedHandler(AccessDeniedException ex) {

        log.info(ex.getMessage());
        return "denied";
    }

    @ExceptionHandler(Exception.class)
    public String AccessDeniedHandler(Exception ex) {

        log.error(ex.getMessage());
        return "unexpectederror";
    }

    @ExceptionHandler(GenericException.class)
    public String verificationFailed(GenericException ex, Model model) {

        StringBuilder errorStr = new StringBuilder();
        errorStr.append(ex.getErrCode());
        errorStr.append(": ");
        errorStr.append(ex.getErrMsg());

        log.info(errorStr.toString());
        model.addAttribute("errCode", ex.getErrCode());
        model.addAttribute("errMsg", ex.getErrMsg());

        return "error/genericerror";
    }
}
