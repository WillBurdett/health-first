package com.healthfirst.memberservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class MemberWithEmailAlreadyExists extends RuntimeException {
    public MemberWithEmailAlreadyExists(String message) {
        super(message);
    }
}
