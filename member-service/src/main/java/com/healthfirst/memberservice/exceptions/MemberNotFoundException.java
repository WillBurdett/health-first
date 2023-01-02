package com.healthfirst.memberservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(String message) {
        super(message);
    }
}
