package com.nrodrigoc.logisticsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class LogisticaApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogisticaApplication.class, args);
    }

}
