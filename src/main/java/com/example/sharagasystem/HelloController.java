package com.example.sharagasystem;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
public class HelloController {

    @GetMapping("")
    public String greet(){
        return "Hello World";
    }
}
