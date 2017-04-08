package ru.andrei.tsystemsverificationwork.web.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ru.andrei.tsystemsverificationwork.web.exceptions.GenericException;
import ru.andrei.tsystemsverificationwork.web.exceptions.impl.VerificationException;

import java.nio.file.AccessDeniedException;


@ControllerAdvice
public class ErrorHandler {

//
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
//
//    @ExceptionHandler(Exception.class)
//    public String AccessDeniedHandler(Exception ex) {
//
//        return "unexpectederror";
//    }
//
//    @ExceptionHandler(GenericException.class)
//    public String verificationFailed(GenericException ex, Model model) {
//
//        model.addAttribute("errCode", ex.getErrCode());
//        model.addAttribute("errMsg", ex.getErrMsg());
//
//        return "error/genericerror";
//    }

}
