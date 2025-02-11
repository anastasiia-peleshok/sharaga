package com.example.sharagasystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.sharagasystem")
public class SharagaSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SharagaSystemApplication.class, args);
    }

}
