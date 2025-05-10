package com.example.sharagasystem.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ExceptionDetails {
    private String[] message;
    private HttpStatus httpStatus;
    private String localDateTime;
    private String path;
}
