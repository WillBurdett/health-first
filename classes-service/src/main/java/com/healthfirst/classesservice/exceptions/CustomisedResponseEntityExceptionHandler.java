package com.healthfirst.classesservice.exceptions;

import java.time.LocalDateTime;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomisedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    //ALL EXCEPTIONS
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionDetails> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
        ExceptionDetails memberErrors = new ExceptionDetails(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<ExceptionDetails>(memberErrors, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //CLASSINFO NOT FOUND EXCEPTION
    @ExceptionHandler(ClassInfoNotFoundException.class)
    public final ResponseEntity<ExceptionDetails> handleClassInfoNotFoundExceptions(Exception ex, WebRequest request) throws Exception {
        ExceptionDetails classInfoErrors = new ExceptionDetails(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<ExceptionDetails>(classInfoErrors, HttpStatus.NOT_FOUND);
    }

    //VALIDATION CONSTRAINTS ERRORS
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        ExceptionDetails memberErrors = new ExceptionDetails(LocalDateTime.now(),
                "Total Errors:" + ex.getErrorCount() + " First Error:" + ex.getFieldError().getDefaultMessage(), request.getDescription(false));

        return new ResponseEntity(memberErrors, HttpStatus.BAD_REQUEST);
    }
}

