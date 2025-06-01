package com.taskgo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TaskGoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskGoApplication.class, args);
    }

}
