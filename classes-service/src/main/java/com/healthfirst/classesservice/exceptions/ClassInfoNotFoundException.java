package com.healthfirst.classesservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ClassInfoNotFoundException extends RuntimeException {
    public ClassInfoNotFoundException(String message) {
        super(message);
    }
}
