package com.healthfirst.memberservice.exceptions;


import com.healthfirst.memberservice.exceptions.ExceptionDetails;
import com.healthfirst.memberservice.exceptions.MemberNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public final ResponseEntity<ExceptionDetails> handleMemberNotFoundExceptions(Exception ex, WebRequest request) throws Exception {
        ExceptionDetails memberErrors = new ExceptionDetails(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<ExceptionDetails>(memberErrors, HttpStatus.NOT_FOUND);
    }

    //VALIDATION CONSTRAINTS ERRORS
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        ExceptionDetails memberErrors = new ExceptionDetails(LocalDateTime.now(),
                "Total Errors:" + ex.getErrorCount() + " First Error:" + ex.getFieldError().getDefaultMessage(), request.getDescription(false));

        return new ResponseEntity(memberErrors, HttpStatus.BAD_REQUEST);
    }
}

