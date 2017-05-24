package ru.andrei.tsystemsverificationwork.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.andrei.tsystemsverificationwork.web.exceptions.GenericException;

import java.nio.file.AccessDeniedException;

/**
 * Handler advices all exceptions
 */
@ControllerAdvice
public class ErrorHandler {

    /**
     * Slf4j factory
     */
    private static final Logger log = LoggerFactory.getLogger(ErrorHandler.class);

    /**
     * Method advices and logs database related exceptions
     *
     * @param ex DataAccessException
     * @return jsp error view
     */
    @ExceptionHandler(DataAccessException.class)
    public String databaseExceptionHandler(DataAccessException ex) {

        log.error(ex.getMessage(), ex);
        return "error";
    }

    /**
     * Method advices and logs access exceptions
     *
     * @param ex AccessDeniedException
     * @return jsp error view
     */
    @ExceptionHandler(AccessDeniedException.class)
    public String accessDeniedHandler(AccessDeniedException ex) {

        log.info(ex.getMessage(), ex);
        return "denied";
    }

    /**
     * Method advices and logs all exceptions
     *
     * @param ex Exception
     * @return jsp error view
     */
    @ExceptionHandler(Throwable.class)
    public String allExceptions(Throwable ex) {

        log.error(ex.getMessage(), ex);
        return "unexpectederror";
    }

    /**
     * Method advices and logs custom exceptions
     *
     * @param ex GenericException
     * @return jsp error view
     */
    @ExceptionHandler(GenericException.class)
    public String verificationFailed(GenericException ex, Model model) {

        log.info("{}: {}", ex.getErrCode(), ex.getErrMsg(), ex);
        model.addAttribute("errCode", ex.getErrCode());
        model.addAttribute("errMsg", ex.getErrMsg());

        return "error/genericerror";
    }
}
