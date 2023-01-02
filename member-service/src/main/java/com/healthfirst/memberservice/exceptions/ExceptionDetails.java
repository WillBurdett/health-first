package com.healthfirst.memberservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDetails {
    private LocalDateTime timestamp;
    private String message;
    private String details;
}
