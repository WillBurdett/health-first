package com.healthfirst.memberservice;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomisedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    //ALL EXCEPTIONS
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionDetails> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
        ExceptionDetails memberErrors = new ExceptionDetails(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<ExceptionDetails>(memberErrors, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //MEMBER NOT FOUND EXCEPTIONS
    @ExceptionHandler(MemberNotFoundException.class)
    public final ResponseEntity<ExceptionDetails> handleMemeberNotFoundExceptions(Exception ex, WebRequest request) throws Exception {
        ExceptionDetails memberErros = new ExceptionDetails(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<ExceptionDetails>(memberErros, HttpStatus.NOT_FOUND);

    }
}
